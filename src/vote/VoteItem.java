package vote;

//immutable
public class VoteItem<C> {

	// 本投票项所针对的候选对象
	private C candidate;
	// 对候选对象的具体投票选项，例如“支持”、“反对”等
	// 无需保存具体数值，需要时可以从投票活动的VoteType对象中查询得到
	private String value;
	private boolean isValid=true;

	// Rep Invariants
	// candidate必须的voteType类的options的key，
	// value必须是“支持”、“反对”、“弃权”中的一种
	// Abstract Function
	// candidate是到候选人的一个映射，value是到投票选项的映射
	// Safety from Rep Exposure
	// value和candidate都是private，不会泄露

	private boolean checkRep() {
		assert value.equals("支持")||value.equals("反对")||value.equals("弃权");
		return false;

	}

	/**
	 * 创建一个投票项对象 例如：针对候选对象“张三”，投票选项是“支持”
	 * 
	 * @param candidate 所针对的候选对象
	 * @param value     所给出的投票选项
	 */
	public VoteItem(C candidate, String value) {
		this.candidate = candidate;
		this.value = value;
		checkRep();
	}

	/**
	 * 得到该投票选项的具体投票项
	 * 
	 * @return 投票选项的具体投票项
	 */
	public String getVoteValue() {
		return this.value;
	}

	/**
	 * 得到该投票选项所对应的候选人
	 * 
	 * @return 该投票选项所对应的候选人
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
