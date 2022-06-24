package app;

import auxiliary.Dish;
import auxiliary.Proposal;
import auxiliary.Voter;
import pattern.BusinessSelection;
import pattern.BusinessStatistics;
import pattern.DinnerSelection;
import pattern.DinnerStatistics;
import poll.Poll;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

import java.util.*;

public class DinnerOrderApp {
	
	public static void main(String[] args) throws Exception {
		Poll<Dish> election=Poll.create("DinnerOrder");
		HashMap<String, Integer> map = new HashMap<>();
		map.put("喜欢", 1);
		map.put("不喜欢", -1);
		map.put("无所谓", 0);
		VoteType voteType = new VoteType(map);
		election.setInfo("DinnerOrder", Calendar.getInstance(), voteType, 3);

		//添加候选人,候选人类型为Dish
		ArrayList<Dish> candidates = new ArrayList<>();
		Dish d1 = new Dish("锅包肉", 38);
		Dish d2 = new Dish("小龙虾", 68);
		Dish d3 = new Dish("毛血旺", 58);
		Dish d4 = new Dish("溜肉段", 38);
		candidates.add(d1);
		candidates.add(d2);
		candidates.add(d3);
		candidates.add(d4);
		election.addCandidates(candidates);

		//投票者
		Map<Voter, Double> voters = new HashMap<>();
		Voter father = new Voter("father");
		Voter mother = new Voter("mother");
		Voter son = new Voter("son");
		voters.put(father, 1.0);
		voters.put(son, 2.0);
		voters.put(mother,1.5);
		election.addVoters(voters);

		//添加投票者对候选人的投票
		HashSet<VoteItem<Dish>> vote1 = new HashSet<>();//第一名投票者的票
		vote1.add(new VoteItem<>(d1, "喜欢"));
		vote1.add(new VoteItem<>(d2,"不喜欢"));
		vote1.add(new VoteItem<>(d3,"无所谓"));
		vote1.add(new VoteItem<>(d4,"喜欢"));
		HashSet<VoteItem<Dish>> vote2 = new HashSet<>();//第2名投票者的票
		vote2.add(new VoteItem<>(d1, "喜欢"));
		vote2.add(new VoteItem<>(d2,"喜欢"));
		vote2.add(new VoteItem<>(d3,"喜欢"));
		vote2.add(new VoteItem<>(d4,"不喜欢"));
		HashSet<VoteItem<Dish>> vote3 = new HashSet<>();//第3名投票者的票
		vote3.add(new VoteItem<>(d1, "无所谓"));
		vote3.add(new VoteItem<>(d2,"喜欢"));
		vote3.add(new VoteItem<>(d3,"喜欢"));
		vote3.add(new VoteItem<>(d4,"不喜欢"));
		Vote<Dish> votesOf1 = new RealNameVote<>(vote1,father);
		Vote<Dish> votesOf2 = new RealNameVote<>(vote2,son);
		Vote<Dish> votesOf3 = new RealNameVote<>(vote3,mother);

		election.addVote(votesOf1);
		election.addVote(votesOf2);
		election.addVote(votesOf3);

		DinnerStatistics ds = new DinnerStatistics();
		election.statistics(ds);
		DinnerSelection dinnerSelection = new DinnerSelection();
		election.selection(dinnerSelection);
		System.out.println(election.result());
	}
}
