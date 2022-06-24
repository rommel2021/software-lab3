package poll;

import static org.junit.jupiter.api.Assertions.*;

import auxiliary.Person;
import auxiliary.Proposal;
import auxiliary.Voter;
import org.junit.jupiter.api.Test;
import pattern.ElectionSelection;
import pattern.ElectionStatistics;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

import java.util.*;

class ElectionTest {
	
	// test strategy
	// TODO
	
	@Test
	void test() throws Exception {
		Poll<Person> election=Poll.create("Election");
		HashMap<String, Integer> map = new HashMap<>();
		map.put("支持", 1);
		map.put("反对", -1);
		map.put("弃权", 0);
		VoteType voteType = new VoteType(map);
		election.setInfo("item", Calendar.getInstance(), voteType, 2);

		//添加候选人,候选人类型为Person
		ArrayList<Person> candidates = new ArrayList<>();
		Person p1 = new Person("tom",20);
		candidates.add(p1);
		Person p2 = new Person("jack",21);
		candidates.add(p2);
		Person p3 = new Person("wow",19);
		candidates.add(p3);
		election.addCandidates(candidates);

		//投票者
		Map<Voter, Double> voters = new HashMap<>();
		voters.put(new Voter("1001"), 1.0);
		voters.put(new Voter("1002"), 1.0);
		election.addVoters(voters);

		//添加投票者对候选人的投票
		HashSet<VoteItem<Person>> vote1 = new HashSet<>();//第一名投票者的票
		vote1.add(new VoteItem<Person>(p1, "支持"));
		vote1.add(new VoteItem<>(p2,"反对"));
		vote1.add(new VoteItem<>(p3,"弃权"));
		HashSet<VoteItem<Person>> vote2 = new HashSet<>();//第2名投票者的票
		vote2.add(new VoteItem<>(p1, "支持"));
		vote2.add(new VoteItem<>(p2,"支持"));
		vote2.add(new VoteItem<>(p3,"弃权"));
		Vote<Person> votesOf1 = new Vote<>(vote1);
		Vote<Person> votesOf2 = new Vote<>(vote2);
		election.addVote(votesOf1);
		election.addVote(votesOf2);

		StatisticsStrategy<Person> es=new ElectionStatistics();
		election.statistics(es);
		SelectionStrategy<Person> ss=new ElectionSelection();
		election.selection(ss);
		System.out.println(election.result());
	}

}
