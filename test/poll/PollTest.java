package poll;

import static org.junit.jupiter.api.Assertions.*;

import auxiliary.Voter;
import org.junit.jupiter.api.Test;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

import java.util.*;
import java.util.function.BooleanSupplier;

class PollTest {

    // test strategy
    // ����һ����������Ϊpoll�Ķ������û�����Ϣ����Ӻ�ѡ�˺�ͶƱ��

    @Test
    void test() {
        Poll<String> poll = Poll.create();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("֧��", 2);
        map.put("����", -2);
        map.put("��Ȩ", 1);
        VoteType voteType = new VoteType(map);
        poll.setInfo("item", Calendar.getInstance(), voteType, 1);

        //��Ӻ�ѡ��,��ѡ������ΪString
        ArrayList<String> candidates = new ArrayList<>();
        candidates.add("item1");
        candidates.add("item2");
        poll.addCandidates(candidates);

        //���ͶƱ��
        Map<Voter, Double> voters = new HashMap<>();
        voters.put(new Voter("1001"), 2.0);
        voters.put(new Voter("1002"), 2.5);
        poll.addVoters(voters);

        //���ͶƱ�߶Ժ�ѡ�˵�ͶƱ
        HashSet<VoteItem<String>> vote1 = new HashSet<>();//��һ��ͶƱ�ߵ�Ʊ
        vote1.add(new VoteItem<>("item1", "֧��"));
        vote1.add(new VoteItem<>("item2", "����"));
        HashSet<VoteItem<String>> vote2 = new HashSet<>();//��2��ͶƱ�ߵ�Ʊ
        vote2.add(new VoteItem<>("item1", "֧��"));
        vote2.add(new VoteItem<>("item2", "֧��"));
        Vote<String> votesOf1 = new Vote<>(vote1);
        Vote<String> votesOf2 = new Vote<>(vote2);
		poll.addVote(votesOf1);
		poll.addVote(votesOf2);

		//�������Ʊ
        poll.statistics(new StatisticsStrategy() {
           //TODO
        });
        poll.selection(new SelectionStrategy() {
            //TODO
        });

        //�жϽ���Ƿ����Ԥ��
//        assertEquals("",poll.result());
    }

}
