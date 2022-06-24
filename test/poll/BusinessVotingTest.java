package poll;

import static org.junit.jupiter.api.Assertions.*;

import auxiliary.Proposal;
import auxiliary.Voter;
import org.junit.jupiter.api.Test;
import pattern.BusinessSelection;
import pattern.BusinessStatistics;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

import java.util.*;

class BusinessVotingTest {

	// test strategy
	// 先创建选举对象，创建投票项，添加候选人，然后增加投票人对每个候选人的选择
	// 依次按照统计策略和选择策略执行，最后输出结果
	
	@Test
	void test() throws Exception {
		Poll businessVote=Poll.create("BusinessVoting");
		HashMap<String, Integer> map = new HashMap<>();
		map.put("支持", 1);
		map.put("反对", -1);
		map.put("弃权", 0);
		VoteType voteType = new VoteType(map);
		businessVote.setInfo("item", Calendar.getInstance(), voteType, 2);

		//添加候选人,候选人类型为Proposal
		ArrayList<Proposal> candidates = new ArrayList<>();
		Proposal proposal = new Proposal("item", Calendar.getInstance());
		Proposal proposal1 = new Proposal("item2",Calendar.getInstance());
		candidates.add(proposal);
		candidates.add(proposal1);
		businessVote.addCandidates(candidates);

		Map<Voter, Double> voters = new HashMap<>();
		Voter v1 = new Voter("1001");
		Voter v2 = new Voter("1002");
		voters.put(v1, 0.3);
		voters.put(v2, 0.7);

		businessVote.addVoters(voters);

		//添加投票者对候选项目的投票
		HashSet<VoteItem<Proposal>> vote1 = new HashSet<>();//第一名投票者的票
		vote1.add(new VoteItem(proposal, "支持"));
		vote1.add(new VoteItem<>(proposal1,"支持"));
		HashSet<VoteItem<Proposal>> vote2 = new HashSet<>();//第2名投票者的票
		vote2.add(new VoteItem(proposal, "支持"));
		vote2.add(new VoteItem(proposal1, "反对"));
		Vote<Proposal> votesOf1 = new RealNameVote<>(vote1,v1);
		Vote<Proposal> votesOf2 = new RealNameVote<>(vote2,v2);
		businessVote.addVote(votesOf1);
		businessVote.addVote(votesOf2);

		BusinessStatistics bs = new BusinessStatistics();
		businessVote.statistics(bs);
		BusinessSelection businessSelection = new BusinessSelection();
		businessVote.selection(businessSelection);
		System.out.println(businessVote.result());

//		businessVote.setInfo();
	}

}
