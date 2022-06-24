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
		map.put("ϲ��", 1);
		map.put("��ϲ��", -1);
		map.put("����ν", 0);
		VoteType voteType = new VoteType(map);
		election.setInfo("DinnerOrder", Calendar.getInstance(), voteType, 3);

		//��Ӻ�ѡ��,��ѡ������ΪDish
		ArrayList<Dish> candidates = new ArrayList<>();
		Dish d1 = new Dish("������", 38);
		Dish d2 = new Dish("С��Ϻ", 68);
		Dish d3 = new Dish("ëѪ��", 58);
		Dish d4 = new Dish("�����", 38);
		candidates.add(d1);
		candidates.add(d2);
		candidates.add(d3);
		candidates.add(d4);
		election.addCandidates(candidates);

		//ͶƱ��
		Map<Voter, Double> voters = new HashMap<>();
		Voter father = new Voter("father");
		Voter mother = new Voter("mother");
		Voter son = new Voter("son");
		voters.put(father, 1.0);
		voters.put(son, 2.0);
		voters.put(mother,1.5);
		election.addVoters(voters);

		//���ͶƱ�߶Ժ�ѡ�˵�ͶƱ
		HashSet<VoteItem<Dish>> vote1 = new HashSet<>();//��һ��ͶƱ�ߵ�Ʊ
		vote1.add(new VoteItem<>(d1, "ϲ��"));
		vote1.add(new VoteItem<>(d2,"��ϲ��"));
		vote1.add(new VoteItem<>(d3,"����ν"));
		vote1.add(new VoteItem<>(d4,"ϲ��"));
		HashSet<VoteItem<Dish>> vote2 = new HashSet<>();//��2��ͶƱ�ߵ�Ʊ
		vote2.add(new VoteItem<>(d1, "ϲ��"));
		vote2.add(new VoteItem<>(d2,"ϲ��"));
		vote2.add(new VoteItem<>(d3,"ϲ��"));
		vote2.add(new VoteItem<>(d4,"��ϲ��"));
		HashSet<VoteItem<Dish>> vote3 = new HashSet<>();//��3��ͶƱ�ߵ�Ʊ
		vote3.add(new VoteItem<>(d1, "����ν"));
		vote3.add(new VoteItem<>(d2,"ϲ��"));
		vote3.add(new VoteItem<>(d3,"ϲ��"));
		vote3.add(new VoteItem<>(d4,"��ϲ��"));
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
