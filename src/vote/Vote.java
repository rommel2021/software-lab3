package vote;

import auxiliary.Voter;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//immutable
public class Vote<C> {

	// ȱʡΪ��������ͶƱ��������Ҫ����ͶƱ�˵���Ϣ
	// һ��Vote�������һ��ͶƱ�߶����к�ѡ�ߵ�ͶƱ

	// һ��ͶƱ�˶����к�ѡ�����ͶƱ���
	private Set<VoteItem<C>> voteItems = new HashSet<>();
	// ͶƱʱ��
	private Calendar date = Calendar.getInstance();
	//ͶƱ����Ϣ
	private Voter voter;


	// Rep Invariants
	// voteItems�е�Ԫ�ر��������к�ѡ�ˣ����ܶ�Ҳ������
	// Abstract Function
	// ��HashSet voteItemsӳ��һ��ͶƱ�߶����к�ѡ�����ͶƱ���
	// date��ʾ����
	// Safety from Rep Exposure
	// voteItems��date��Ϊ˽�У����ᷢ��й¶

	private boolean checkRep() {
		// TODO
		return false;
	}

	/**
	 * ����һ��ѡƱ����
	 * 
	 * ����������Ƹ÷��������õĲ���
	 * 
	 */
	public Vote(/* TODO */) {
		// TODO

	}

	public Vote(HashSet<VoteItem<C>> candidates) {
		// TODO
		voteItems=candidates;
	}

	public Vote(HashSet<VoteItem<C>> candidates,Voter v){
		voteItems=candidates;
		voter=v;
	}

	/** done
	 * ��ѯ��ѡƱ�а���������ͶƱ��
	 * 
	 * @return ����ͶƱ��
	 */
	public Set<VoteItem<C>> getVoteItems() {
		// TODO
		return voteItems;
	}

	/**
	 * һ���ض���ѡ���Ƿ������ѡƱ��
	 * 
	 * @param candidate ����ѯ�ĺ�ѡ��
	 * @return �������ú�ѡ�˵�ͶƱ��򷵻�true������false
	 */
	public boolean candidateIncluded(C candidate) {
		// TODO

		return false;
	}

	@Override
	public int hashCode() {
		// TODO
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO
		return true;
	}
}
