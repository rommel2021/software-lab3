package app;

import java.util.*;

import auxiliary.Person;
import auxiliary.Voter;
import pattern.ElectionSelection;
import pattern.ElectionStatistics;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import poll.Poll;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;


public class ElectionApp {

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		// 创建投票活动
		Poll<Person> poll = Poll.create("Election");
		// 设定投票类型
		Map<String, Integer> types = new HashMap<>();
		types.put("Support", 1);
		types.put("Oppose", -1);
		types.put("Waive", 0);
		VoteType vt = new VoteType(types);
		// 设定投票基本信息：名称、日期、投票类型、选出的数量
		poll.setInfo("electAPP",Calendar.getInstance(),vt,1);
		// 创建候选对象：候选人
		Person p1 = new Person("ABC", 19);
		Person p2 = new Person("DEF", 20);
		Person p3 = new Person("GHI", 21);
		ArrayList<Person> candidates = new ArrayList<>();
		candidates.add(p1);
		candidates.add(p2);
		candidates.add(p3);
		poll.addCandidates(candidates);
		// 创建2个投票人
		Voter vr1 = new Voter("v1");
		Voter vr2 = new Voter("v2");
		// 设定2个投票人的权重
		Map<Voter, Double> weightedVoters = new HashMap<>();
		weightedVoters.put(vr1, 1.0);
		weightedVoters.put(vr2, 1.0);

		// 创建投票项，前三个是投票人vr1对三个候选对象的投票项，后三个是vr2的投票项
		VoteItem<Person> vi11 = new VoteItem<>(p1, "Support");
		VoteItem<Person> vi12 = new VoteItem<>(p2, "Oppose");
		VoteItem<Person> vi13 = new VoteItem<>(p3, "Support");
		VoteItem<Person> vi21 = new VoteItem<>(p1, "Oppose");
		VoteItem<Person> vi22 = new VoteItem<>(p2, "Waive");
		VoteItem<Person> vi23 = new VoteItem<>(p3, "Waive");

		// 将选票加入投票活动
		// voteItemVr1是投票人vr1的选票
		HashSet<VoteItem<Person>> voteItemsVr1 = new HashSet<VoteItem<Person>>();
		voteItemsVr1.add(vi11);
		voteItemsVr1.add(vi12);
		voteItemsVr1.add(vi13);
		HashSet<VoteItem<Person>> voteItemsVr2 = new HashSet<VoteItem<Person>>();
		voteItemsVr2.add(vi21);
		voteItemsVr2.add(vi22);
		voteItemsVr2.add(vi23);
		Vote<Person> rv1 = new Vote<>(voteItemsVr1);
		Vote<Person> rv2 = new Vote<>(voteItemsVr2);


		// 增加投票人及其权重
		poll.addVoters(weightedVoters);
		// 增加三个投票人的选票
		poll.addVote(rv1);
		poll.addVote(rv2);

		// 按规则计票
		ElectionStatistics es = new ElectionStatistics();
		poll.statistics(es);
		// 按规则遴选
		ElectionSelection esl = new ElectionSelection();
		poll.selection(esl);
		// 输出遴选结果
		System.out.println(poll.result());
	}

}
