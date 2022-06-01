package vote;

import auxiliary.Voter;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//immutable
public class Vote<C> {

	// 缺省为“匿名”投票，即不需要管理投票人的信息
	// 一个Vote对象就是一个投票者对所有候选者的投票

	// 一个投票人对所有候选对象的投票项集合
	private Set<VoteItem<C>> voteItems = new HashSet<>();
	// 投票时间
	private Calendar date = Calendar.getInstance();
	//投票人信息
	private Voter voter;


	// Rep Invariants
	// voteItems中的元素必须是所有候选人，不能多也不能少
	// Abstract Function
	// 用HashSet voteItems映射一个投票者对所有候选对象的投票项集合
	// date表示日期
	// Safety from Rep Exposure
	// voteItems和date均为私有，不会发生泄露

	private boolean checkRep() {
		// TODO
		return false;
	}

	/**
	 * 创建一个选票对象
	 * 
	 * 可以自行设计该方法所采用的参数
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
	 * 查询该选票中包含的所有投票项
	 * 
	 * @return 所有投票项
	 */
	public Set<VoteItem<C>> getVoteItems() {
		// TODO
		return voteItems;
	}

	/**
	 * 一个特定候选人是否包含本选票中
	 * 
	 * @param candidate 待查询的候选人
	 * @return 若包含该候选人的投票项，则返回true，否则false
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
