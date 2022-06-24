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
	// TODO
	
	@Test
	void test() throws Exception {
		Poll businessVote=Poll.create("BusinessVoting");
		HashMap<String, Integer> map = new HashMap<>();
		map.put("支持", 1);
		map.put("反对", -1);
		map.put("弃权", 0);
		VoteType voteType = new VoteType(map);
		businessVote.setInfo("item", Calendar.getInstance(), voteType, 1);

		//添加候选人,候选人类型为Proposal
		ArrayList<Proposal> candidates = new ArrayList<>();
		Proposal proposal = new Proposal("item", Calendar.getInstance());
		candidates.add(proposal);
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
		HashSet<VoteItem<Proposal>> vote2 = new HashSet<>();//第2名投票者的票
		vote2.add(new VoteItem(proposal, "支持"));
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
