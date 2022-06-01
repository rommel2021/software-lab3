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
    // 创建一个引用类型为poll的对象，设置基本信息，添加候选人和投票者

    @Test
    void test() {
        Poll<String> poll = Poll.create();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("支持", 2);
        map.put("反对", -2);
        map.put("弃权", 1);
        VoteType voteType = new VoteType(map);
        poll.setInfo("item", Calendar.getInstance(), voteType, 1);

        //添加候选人,候选人类型为String
        ArrayList<String> candidates = new ArrayList<>();
        candidates.add("item1");
        candidates.add("item2");
        poll.addCandidates(candidates);

        //添加投票者
        Map<Voter, Double> voters = new HashMap<>();
        voters.put(new Voter("1001"), 2.0);
        voters.put(new Voter("1002"), 2.5);
        poll.addVoters(voters);

        //添加投票者对候选人的投票
        HashSet<VoteItem<String>> vote1 = new HashSet<>();//第一名投票者的票
        vote1.add(new VoteItem<>("item1", "支持"));
        vote1.add(new VoteItem<>("item2", "反对"));
        HashSet<VoteItem<String>> vote2 = new HashSet<>();//第2名投票者的票
        vote2.add(new VoteItem<>("item1", "支持"));
        vote2.add(new VoteItem<>("item2", "支持"));
        Vote<String> votesOf1 = new Vote<>(vote1);
        Vote<String> votesOf2 = new Vote<>(vote2);
		poll.addVote(votesOf1);
		poll.addVote(votesOf2);

		//按规则计票
        poll.statistics(new StatisticsStrategy() {
           //TODO
        });
        poll.selection(new SelectionStrategy() {
            //TODO
        });

        //判断结果是否符合预期
//        assertEquals("",poll.result());
    }

}
