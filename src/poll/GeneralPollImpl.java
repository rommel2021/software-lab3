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
	private String name;
	// ͶƱ������ʱ��
	private Calendar date;
	// ��ѡ���󼯺�
	private List<C> candidates;
	// ͶƱ�˼��ϣ�keyΪͶƱ�ˣ�valueΪ���ڱ���ͶƱ����ռȨ��
	private Map<Voter, Double> voters;
	// ��ѡ���ĺ�ѡ�����������
	private int quantity;
	// ����ͶƱ����õ�ͶƱ���ͣ��Ϸ�ѡ����Զ�Ӧ�ķ�����
	private VoteType voteType;
	// ����ѡƱ����
	protected Set<Vote> votes;
	// ��Ʊ�����keyΪ��ѡ����valueΪ��÷�
	protected Map<C, Double> statistics;
	// ��ѡ�����keyΪ��ѡ����valueΪ������λ��
	private Map<C, Double> results;

	// Rep Invariants
	// name ����Ϊ��
	// date ����Ϊ��
	// candidates��voters Ԫ����������0
	// quantity ����0С�ڵ���candidatesԪ������
	// votes �������ڲ�ѡ������
	// statistics��statistics��Ԫ�ظ������ں�ѡ������
	// resultsԪ����������quantity
	// Abstract Function
	// ����һ��GeneralPolllmpl�ൽͶƱ�����ӳ��
	// Safety from Rep Exposure
	// name/date,quantity,votes,statistics���������Զ�Ϊprivate������й¶

	private boolean checkRep() {
		// TODO
		assert name!=null;
		assert date!=null;
		assert candidates.size()>0;
		assert voters.size()>0;
		assert quantity>0;
		assert quantity<=candidates.size();
		assert votes.size()== voters.size();
		assert statistics.size()== candidates.size();
		assert results.size()==candidates.size();
		return false;
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
		this.name=name;
		this.date=date;
		this.voteType=type;
		this.quantity=quantity;
		votes=new HashSet<Vote>();
		checkRep();
	}

	@Override
	public void addVoters(Map<Voter, Double> voters) {
		// TODO
		this.voters=voters;
		checkRep();
	}

	@Override
	public void addCandidates(List<C> candidates) {
		// TODO
		this.candidates=candidates;
		checkRep();
	}

	@Override
	public void addVote(Vote<C> vote) {
		// �˴�Ӧ���ȼ���ѡƱ�ĺϷ��Բ���ǣ�Ϊ������չ���޸�rep
		// Ϊ����Ҫ�޸�voteItem���rep?
		for(VoteItem voteItem:vote.getVoteItems()){
			if(!this.voteType.checkLegality(voteItem.getVoteValue())){
				voteItem.setValid(false);
			}
		}
		votes.add(vote);
		checkRep();
	}

	@Override
	public void statistics(StatisticsStrategy ss) {
		// �˴�Ӧ���ȼ�鵱ǰ��ӵ�е�ѡƱ�ĺϷ���
		// TODO

	}

	@Override
	public void selection(SelectionStrategy ss) {
		// TODO
	}

	@Override
	public String result() {
		// TODO
		return null;
	}


	// Rep Invariants
	// GeneralPolllmpl��RI
	// ���йɶ���Ȩ��֮�ͱ���Ϊ1
	// Abstract Function
	//
	// Safety from Rep Exposure
	// GeneralPolllmpl����������з�ֹ��ʾй¶���ֶ�
	//TODO

}
