package app;

import auxiliary.Proposal;
import auxiliary.Voter;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import poll.Poll;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;

public class BusinessVotingApp {


	public static void main(String[] args) throws Exception {
		// TODO
		// ����3���ɶ�
		Voter s1 = new Voter("s1");
		Voter s2 = new Voter("s2");
		Voter s3 = new Voter("s3");

		//����Ȩ��
		HashMap<Voter, Double> holderWeight = new HashMap<>();
		holderWeight.put(s1,0.33);
		holderWeight.put(s2,0.2);
		holderWeight.put(s3,0.47);
		double sum=0;
		for(double weight:holderWeight.values()){
			sum+=weight;
		}
		if(sum!=1.0)
			throw new RuntimeException("total weight must be 1.0");

		//�趨ͶƱ����
		HashMap<String, Integer> types = new HashMap<>();
		types.put("support",1);
		types.put("oppose",-1);
		types.put("waive",0);
		VoteType voteType = new VoteType(types);

		//������ѡ�᰸
		Proposal proposal = new Proposal("item", Calendar.getInstance());

		//����ͶƱ��
		VoteItem<Proposal> v1 = new VoteItem<Proposal>(proposal,"support");
		VoteItem<Proposal> v2 = new VoteItem<Proposal>(proposal,"support");
		VoteItem<Proposal> v3 = new VoteItem<Proposal>(proposal,"oppose");

		//����3���ɶ���ѡƱ,ʵ����
		HashSet<VoteItem<Proposal>> set1 = new HashSet<>();
		set1.add(v1);
		Vote<Proposal> vote1 = new Vote<>(set1,s1);
		HashSet<VoteItem<Proposal>> set2 = new HashSet<>();
		set1.add(v2);
		Vote<Proposal> vote2 = new Vote<>(set2,s2);
		HashSet<VoteItem<Proposal>> set3 = new HashSet<>();
		set1.add(v3);
		Vote<Proposal> vote3 = new Vote<>(set3,s3);

		//����ͶƱ�
		Poll<Proposal> poll = Poll.create();
		poll.setInfo("voteProposal",Calendar.getInstance(),voteType,1);
		poll.addVoters(holderWeight);
		poll.addVote(vote1);
		poll.addVote(vote2);
		poll.addVote(vote3);

		poll.statistics(new StatisticsStrategy() {
			//TODO
		});
		// ��������ѡ
		poll.selection(new SelectionStrategy() {
			//TODO
		});
		// �����ѡ���
		System.out.println(poll.result());
	}
}
