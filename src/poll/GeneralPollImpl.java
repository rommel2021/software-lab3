package poll;

import java.util.*;

import auxiliary.Voter;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import vote.VoteItem;
import vote.VoteType;
import vote.Vote;

public class GeneralPollImpl<C> implements Poll<C> {

    // ͶƱ�������
    String name;
    // ͶƱ������ʱ��
    Calendar date;
    // ��ѡ���󼯺�
    List<C> candidates;
    // ͶƱ�˼��ϣ�keyΪͶƱ�ˣ�valueΪ���ڱ���ͶƱ����ռȨ��
    Map<Voter, Double> voters;
    // ��ѡ���ĺ�ѡ�����������
    int quantity;
    // ����ͶƱ����õ�ͶƱ���ͣ��Ϸ�ѡ����Զ�Ӧ�ķ�����
    VoteType voteType;
    // ����ѡƱ����
    Set<Vote<C>> votes;
    // ��Ʊ�����keyΪ��ѡ����valueΪ��÷�
    Map<C, Double> statistics;
    // ��ѡ�����keyΪ��ѡ����valueΪ������λ��
    Map<C, Double> results;

    // Rep Invariants
    // name ����Ϊ��
    // date ����Ϊ��
    // quantity ����0С�ڵ���candidatesԪ������
    // votes �������ڲ�ѡ������
    // statistics��statistics��Ԫ�ظ������ں�ѡ������
    // resultsԪ����������quantity
    // Abstract Function
    // ����һ��GeneralPollImpl�ൽͶƱ�����ӳ��
    // Safety from Rep Exposure
    // name/date,quantity,votes,statistics���������Զ�Ϊȱʡ�����ᱻ�������е���ֱ�ӷ���

    public void checkRep() {
        assert name != null;
        assert date != null;
        assert quantity > 0;
        for (Vote<C> v : votes) {
            //һ��ѡƱ��������к�ѡ��
            for (VoteItem<C> vi : v.getVoteItems()) {
                assert candidates.contains( vi.getCandidate());
            }
            assert v.getVoteItems().size() == candidates.size();
        }
    }

    /**
     * ���캯��
     */
    public GeneralPollImpl() {

    }


    @Override
    public void setInfo(String name, Calendar date, VoteType type, int quantity) {
        this.name = name;
        this.date = date;
        this.voteType = type;
        this.quantity = quantity;
        votes = new HashSet<Vote<C>>();
        checkRep();
    }

    @Override
    public void addVoters(Map<Voter, Double> voters) {
        // TODO
        this.voters = voters;
        checkRep();
    }

    @Override
    public void addCandidates(List<C> candidates) {
        // TODO
        this.candidates = candidates;
        checkRep();
    }

    @Override
    public void addVote(Vote<C> vote) throws Exception {
        // �˴�Ӧ���ȼ���ѡƱ�ĺϷ��Բ���ǣ�Ϊ������չ���޸�rep
        // һ��ѡƱҪ�������к�ѡ�� done
        // ���������ں�ѡ���е��� done
        // ��������ֵ�ѡ�֧�ֲ�֧�֣�done
        // �����ظ���һ����ͶƱ
        // Ϊ����Ҫ�޸�voteItem���rep?

        HashSet<C> cs = new HashSet<>();
        for (VoteItem<C> voteItem : vote.getVoteItems()) {
            cs.add(voteItem.getCandidate());
            if(!candidates.contains((C)voteItem.getCandidate())){
                voteItem.setValid(false);
                vote.setIsLegal(false);
                return;
            }

//                throw new Exception("���ڷǺ�ѡ�ߣ���");
            if (!this.voteType.checkLegality(voteItem.getVoteValue())) {
                voteItem.setValid(false);
                vote.setIsLegal(false);
                return;
            }
        }
        if(cs.size()!=vote.getVoteItems().size()){
            vote.setIsLegal(false);
            return;
        }

//            throw new Exception("���ܳ����ظ�ͶƱ��");
        votes.add(vote);
        checkRep();
    }

    @Override
    public void statistics(StatisticsStrategy ss) {
        // �˴�Ӧ���ȼ�鵱ǰ��ӵ�е�ѡƱ�ĺϷ���
        statistics=new HashMap<>();
        ss.totalScore(statistics,votes,voteType,voters);
        checkRep();
    }

    @Override
    public void selection(SelectionStrategy ss) {
        results=new HashMap<>();
        ss.select(statistics,results,quantity);
        checkRep();
    }

    @Override
    public String result() {
        String str="";
        for(C candidate:results.keySet()){
            str+=candidate.toString();
        }
        return str;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        checkRep();
    }
    @Override
    public Set<Vote<C>> getLegalVotes() {
        HashSet<Vote<C>> cs = new HashSet<>();
        for(Vote<C> v:votes){
            if(v.getIsLegal() == true)
                cs.add(v);
        }
        return cs;
    }
    public Set<Vote<C>> getVotes() {
        return new HashSet<Vote<C>>(votes);
    }
}
