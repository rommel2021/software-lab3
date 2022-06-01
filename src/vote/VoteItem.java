package vote;

//immutable
public class VoteItem<C> {

	// ��ͶƱ������Եĺ�ѡ����
	private C candidate;
	// �Ժ�ѡ����ľ���ͶƱѡ����硰֧�֡��������ԡ���
	// ���豣�������ֵ����Ҫʱ���Դ�ͶƱ���VoteType�����в�ѯ�õ�
	private String value;
	private boolean isValid=true;

	// Rep Invariants
	// candidate�����voteType���options��key��
	// value�����ǡ�֧�֡��������ԡ�������Ȩ���е�һ��
	// Abstract Function
	// candidate�ǵ���ѡ�˵�һ��ӳ�䣬value�ǵ�ͶƱѡ���ӳ��
	// Safety from Rep Exposure
	// value��candidate����private������й¶

	private boolean checkRep() {
		assert value.equals("֧��")||value.equals("����")||value.equals("��Ȩ");
		return false;

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
