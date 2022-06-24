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

		// ����ͶƱ�
		Poll<Person> poll = Poll.create("Election");
		// �趨ͶƱ����
		Map<String, Integer> types = new HashMap<>();
		types.put("Support", 1);
		types.put("Oppose", -1);
		types.put("Waive", 0);
		VoteType vt = new VoteType(types);
		// �趨ͶƱ������Ϣ�����ơ����ڡ�ͶƱ���͡�ѡ��������
		poll.setInfo("electAPP",Calendar.getInstance(),vt,1);
		// ������ѡ���󣺺�ѡ��
		Person p1 = new Person("ABC", 19);
		Person p2 = new Person("DEF", 20);
		Person p3 = new Person("GHI", 21);
		ArrayList<Person> candidates = new ArrayList<>();
		candidates.add(p1);
		candidates.add(p2);
		candidates.add(p3);
		poll.addCandidates(candidates);
		// ����2��ͶƱ��
		Voter vr1 = new Voter("v1");
		Voter vr2 = new Voter("v2");
		// �趨2��ͶƱ�˵�Ȩ��
		Map<Voter, Double> weightedVoters = new HashMap<>();
		weightedVoters.put(vr1, 1.0);
		weightedVoters.put(vr2, 1.0);

		// ����ͶƱ�ǰ������ͶƱ��vr1��������ѡ�����ͶƱ���������vr2��ͶƱ��
		VoteItem<Person> vi11 = new VoteItem<>(p1, "Support");
		VoteItem<Person> vi12 = new VoteItem<>(p2, "Oppose");
		VoteItem<Person> vi13 = new VoteItem<>(p3, "Support");
		VoteItem<Person> vi21 = new VoteItem<>(p1, "Oppose");
		VoteItem<Person> vi22 = new VoteItem<>(p2, "Waive");
		VoteItem<Person> vi23 = new VoteItem<>(p3, "Waive");

		// ��ѡƱ����ͶƱ�
		// voteItemVr1��ͶƱ��vr1��ѡƱ
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


		// ����ͶƱ�˼���Ȩ��
		poll.addVoters(weightedVoters);
		// ��������ͶƱ�˵�ѡƱ
		poll.addVote(rv1);
		poll.addVote(rv2);

		// �������Ʊ
		ElectionStatistics es = new ElectionStatistics();
		poll.statistics(es);
		// ��������ѡ
		ElectionSelection esl = new ElectionSelection();
		poll.selection(esl);
		// �����ѡ���
		System.out.println(poll.result());
	}

}
