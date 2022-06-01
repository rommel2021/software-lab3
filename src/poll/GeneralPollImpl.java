package poll;

import java.util.*;

import auxiliary.Voter;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import vote.VoteItem;
import vote.VoteType;
import vote.Vote;

public class GeneralPollImpl<C> implements Poll<C> {

	// 投票活动的名称
	private String name;
	// 投票活动发起的时间
	private Calendar date;
	// 候选对象集合
	private List<C> candidates;
	// 投票人集合，key为投票人，value为其在本次投票中所占权重
	private Map<Voter, Double> voters;
	// 拟选出的候选对象最大数量
	private int quantity;
	// 本次投票拟采用的投票类型（合法选项及各自对应的分数）
	private VoteType voteType;
	// 所有选票集合
	protected Set<Vote> votes;
	// 计票结果，key为候选对象，value为其得分
	protected Map<C, Double> statistics;
	// 遴选结果，key为候选对象，value为其排序位次
	private Map<C, Double> results;

	// Rep Invariants
	// name 不能为空
	// date 不能为空
	// candidates、voters 元素数量大于0
	// quantity 大于0小于等于candidates元素数量
	// votes 数量等于参选人数量
	// statistics、statistics的元素个数等于候选者数量
	// results元素数量等于quantity
	// Abstract Function
	// 建立一个GeneralPolllmpl类到投票问题的映射
	// Safety from Rep Exposure
	// name/date,quantity,votes,statistics等所有属性都为private，不会泄露

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
	 * 构造函数
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
		// 此处应首先检查该选票的合法性并标记，为此需扩展或修改rep
		// 为此需要修改voteItem类的rep?
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
		// 此处应首先检查当前所拥有的选票的合法性
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
	// GeneralPolllmpl的RI
	// 所有股东的权重之和必须为1
	// Abstract Function
	//
	// Safety from Rep Exposure
	// GeneralPolllmpl所满足的所有防止表示泄露的手段
	//TODO

}
