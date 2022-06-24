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
    Set<Vote> votes;
    // ��Ʊ�����keyΪ��ѡ����valueΪ��÷�
    Map<C, Double> statistics;
    // ��ѡ�����keyΪ��ѡ����valueΪ������λ��
    Map<C, Double> results;

    // Rep Invariants
    // name ����Ϊ��
    // date ����Ϊ��
    // candidates��voters Ԫ����������0
    // quantity ����0С�ڵ���candidatesԪ������
    // votes �������ڲ�ѡ������
    // statistics��statistics��Ԫ�ظ������ں�ѡ������
    // resultsԪ����������quantity
    // Abstract Function
    // ����һ��GeneralPollImpl�ൽͶƱ�����ӳ��
    // Safety from Rep Exposure
    // name/date,quantity,votes,statistics���������Զ�Ϊȱʡ�����ᱻ�������е���ֱ�ӷ���

    public void checkRep() {
        // TODO
        assert name != null;
        assert date != null;
        assert candidates.size() > 0;
        assert voters.size() > 0;
        assert quantity > 0;
        assert quantity <= candidates.size();
        assert votes.size() == voters.size();
        for (Vote v : votes) {
            //һ��ѡƱ��������к�ѡ��
            for (Object vi : v.getVoteItems()) {
                assert candidates.contains(((VoteItem) vi).getCandidate());
            }
            assert v.getVoteItems().size() == candidates.size();
        }
        assert statistics.size() == candidates.size();
        assert results.size() == candidates.size();


    }

    /**
     * ���캯��
     */
    public GeneralPollImpl() {
        // TODO

    }


    @Override
    public void setInfo(String name, Calendar date, VoteType type, int quantity) {
        // TODO
        this.name = name;
        this.date = date;
        this.voteType = type;
        this.quantity = quantity;
        votes = new HashSet<Vote>();
//        checkRep();
    }

    @Override
    public void addVoters(Map<Voter, Double> voters) {
        // TODO
        this.voters = voters;
//        checkRep();
    }

    @Override
    public void addCandidates(List<C> candidates) {
        // TODO
        this.candidates = candidates;
//        checkRep();
    }

    @Override
    public void addVote(Vote<C> vote) throws Exception {
        // �˴�Ӧ���ȼ���ѡƱ�ĺϷ��Բ���ǣ�Ϊ������չ���޸�rep
        // һ��ѡƱҪ�������к�ѡ�� done
        // ���������ں�ѡ���е��� done
        // ��������ֵ�ѡ�֧�ֲ�֧�֣�done
        // �����ظ���һ����ͶƱ ???
        // Ϊ����Ҫ�޸�voteItem���rep?

//        for(C candidate:candidates){
//            for(VoteItem<C> vi:vote.getVoteItems()){
//                if(!vi.getCandidate().equals(candidate))
//                    return;
////                    throw new Exception("ѡƱ��û�а������к�ѡ�ˣ�");
//            }
//        }
        HashSet<C> cs = new HashSet<>();
        for (VoteItem<C> voteItem : vote.getVoteItems()) {
            cs.add(voteItem.getCandidate());
            if(!candidates.contains((C)voteItem.getCandidate())){
                voteItem.setValid(false);
                return;
            }

//                throw new Exception("���ڷǺ�ѡ�ߣ���");
            if (!this.voteType.checkLegality(voteItem.getVoteValue())) {
                voteItem.setValid(false);
                return;
            }
        }
        if(cs.size()!=vote.getVoteItems().size())
            return;
//            throw new Exception("���ܳ����ظ�ͶƱ��");
        votes.add(vote);
//        checkRep();
    }

    @Override
    public void statistics(StatisticsStrategy ss) {
        // �˴�Ӧ���ȼ�鵱ǰ��ӵ�е�ѡƱ�ĺϷ���
        statistics=new HashMap<>();
        ss.totalScore(statistics,votes,voteType,voters);
    }

    @Override
    public void selection(SelectionStrategy ss) {
        results=new HashMap<>();
        ss.select(statistics,results,quantity);
    }

    @Override
    public String result() {
        String str="";
        for(C candidate:results.keySet()){
            str+=candidate.toString();
        }
        return str;
    }

}
