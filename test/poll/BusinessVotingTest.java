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
	// �ȴ���ѡ�ٶ��󣬴���ͶƱ���Ӻ�ѡ�ˣ�Ȼ������ͶƱ�˶�ÿ����ѡ�˵�ѡ��
	// ���ΰ���ͳ�Ʋ��Ժ�ѡ�����ִ�У����������
	
	@Test
	void test() throws Exception {
		Poll businessVote=Poll.create("BusinessVoting");
		HashMap<String, Integer> map = new HashMap<>();
		map.put("֧��", 1);
		map.put("����", -1);
		map.put("��Ȩ", 0);
		VoteType voteType = new VoteType(map);
		businessVote.setInfo("item", Calendar.getInstance(), voteType, 2);

		//��Ӻ�ѡ��,��ѡ������ΪProposal
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

		//���ͶƱ�߶Ժ�ѡ��Ŀ��ͶƱ
		HashSet<VoteItem<Proposal>> vote1 = new HashSet<>();//��һ��ͶƱ�ߵ�Ʊ
		vote1.add(new VoteItem(proposal, "֧��"));
		vote1.add(new VoteItem<>(proposal1,"֧��"));
		HashSet<VoteItem<Proposal>> vote2 = new HashSet<>();//��2��ͶƱ�ߵ�Ʊ
		vote2.add(new VoteItem(proposal, "֧��"));
		vote2.add(new VoteItem(proposal1, "����"));
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
