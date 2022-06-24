package vote;

import auxiliary.Voter;

import java.util.*;

//immutable
public class Vote<C> implements VoteInterface<C>{

	// ȱʡΪ��������ͶƱ��������Ҫ����ͶƱ�˵���Ϣ
	// һ��Vote�������һ��ͶƱ�߶����к�ѡ�ߵ�ͶƱ
	// һ��ͶƱ�˶����к�ѡ�����ͶƱ���
	Set<VoteItem<C>> voteItems ;
	// ͶƱʱ��
	Calendar date;
	//ͶƱ����Ϣ
	private Voter voter;


	// Rep Invariants
	// voteItems��Ϊ��
	// Abstract Function
	// ��HashSet voteItemsӳ��һ��ͶƱ�߶����к�ѡ�����ͶƱ���
	// date��ʾ����
	// Safety from Rep Exposure
	// voteItems��date,voter��Ϊ˽�У�����ʱ��list��Ϊ�����޸ĵģ����ᷢ��й¶

	private void checkRep() {
		// TODO
		assert !voteItems.isEmpty();
	}

	/**
	 * ����һ��ѡƱ����
	 * 
	 * ����������Ƹ÷��������õĲ���
	 * 
	 */


	public Vote(HashSet<VoteItem<C>> candidates) {
		// TODO
		voteItems=candidates;
		date=Calendar.getInstance();
		checkRep();
	}

//	public Vote(HashSet<VoteItem<C>> candidates,Voter v){
//		voteItems=candidates;
//		voter=v;
//		date=Calendar.getInstance();
//		checkRep();
//	}

	/** done
	 * ��ѯ��ѡƱ�а���������ͶƱ��
	 * 
	 * @return ����ͶƱ��
	 */
	public Set<VoteItem<C>> getVoteItems() {

//		return new HashSet<VoteItem<C>>(voteItems);
		return Collections.unmodifiableSet(voteItems);
	}

	/** done
	 * һ���ض���ѡ���Ƿ������ѡƱ��
	 * 
	 * @param candidate ����ѯ�ĺ�ѡ��
	 * @return �������ú�ѡ�˵�ͶƱ��򷵻�true������false
	 */
	public boolean candidateIncluded(C candidate) {
		Set set=this.getVoteItems();
		for(Object o:set){
			VoteItem vi=(VoteItem) o;
			if(vi.getCandidate().equals(candidate))
				return true;
		}
		return false;
	}

	public Voter getVoter(){
		return voter;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vote<?> vote = (Vote<?>) o;
		return Objects.equals(voteItems, vote.voteItems) && Objects.equals(date, vote.date) ;
	}

	@Override
	public int hashCode() {
		return Objects.hash(voteItems, date);
	}
}
