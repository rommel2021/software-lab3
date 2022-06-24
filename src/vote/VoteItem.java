package vote;

import java.util.Objects;

//immutable
public class VoteItem<C> {

	// ��ͶƱ������Եĺ�ѡ����
	// ��һ��ͶƱ�˶�һ����ѡ�˵�ѡ��
	private C candidate;
	// �Ժ�ѡ����ľ���ͶƱѡ����硰֧�֡��������ԡ���
	// ���豣�������ֵ����Ҫʱ���Դ�ͶƱ���VoteType�����в�ѯ�õ�
	private String value;
	private boolean isValid=true;

	// Rep Invariants
	// candidate��Ϊ�գ�
	// value��Ϊ��
	// Abstract Function
	// candidate�ǵ���ѡ�˵�һ��ӳ�䣬value�ǵ�ͶƱѡ���ӳ��
	// Safety from Rep Exposure
	// value��candidate����private������candidate��value��isValid���ǲ��ɱ����͵ģ�����й¶

	private void checkRep() {
		assert value!=null;
		assert candidate!=null;
	}

	/**
	 * ����һ��ͶƱ����� ���磺��Ժ�ѡ������������ͶƱѡ���ǡ�֧�֡�
	 * 
	 * @param candidate ����Եĺ�ѡ����
	 * @param value     ��������ͶƱѡ��
	 */
	public VoteItem(C candidate, String value) {
		this.candidate = candidate;
		this.value = value;
		checkRep();
	}

	/**
	 * �õ���ͶƱѡ��ľ���ͶƱ��
	 * 
	 * @return ͶƱѡ��ľ���ͶƱ��
	 */
	public String getVoteValue() {
		return this.value;
	}

	/**
	 * �õ���ͶƱѡ������Ӧ�ĺ�ѡ��
	 * 
	 * @return ��ͶƱѡ������Ӧ�ĺ�ѡ��
	 */
	public C getCandidate() {
		return this.candidate;
	}

	public void setValid(boolean valid) {
		isValid = valid;
		checkRep();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		VoteItem<?> voteItem = (VoteItem<?>) o;
		return isValid == voteItem.isValid && Objects.equals(candidate, voteItem.candidate) && Objects.equals(value, voteItem.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(candidate, value, isValid);
	}
}
