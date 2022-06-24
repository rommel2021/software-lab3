package vote;

import auxiliary.Voter;

import java.util.*;

//immutable
public class Vote<C> implements VoteInterface<C>{

	// 缺省为“匿名”投票，即不需要管理投票人的信息
	// 一个Vote对象就是一个投票者对所有候选者的投票
	// 一个投票人对所有候选对象的投票项集合
	Set<VoteItem<C>> voteItems ;
	// 投票时间
	Calendar date;
	//投票人信息
	private Voter voter;


	// Rep Invariants
	// voteItems不为空
	// Abstract Function
	// 用HashSet voteItems映射一个投票者对所有候选对象的投票项集合
	// date表示日期
	// Safety from Rep Exposure
	// voteItems和date,voter均为私有，返回时将list改为不可修改的，不会发生泄露

	private void checkRep() {
		// TODO
		assert !voteItems.isEmpty();
	}

	/**
	 * 创建一个选票对象
	 * 
	 * 可以自行设计该方法所采用的参数
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
	 * 查询该选票中包含的所有投票项
	 * 
	 * @return 所有投票项
	 */
	public Set<VoteItem<C>> getVoteItems() {

//		return new HashSet<VoteItem<C>>(voteItems);
		return Collections.unmodifiableSet(voteItems);
	}

	/** done
	 * 一个特定候选人是否包含本选票中
	 * 
	 * @param candidate 待查询的候选人
	 * @return 若包含该候选人的投票项，则返回true，否则false
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
