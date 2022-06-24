package vote;

import static org.junit.jupiter.api.Assertions.*;

import auxiliary.Dish;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

class VoteTest {

    // test strategy
    // 先创建一个有两个候选者的Vote
    // 判断获得的VoteItems和自己的voteItems是否相同
    // 再判断自己创建的dish是否包含在Vote中

    @Test
    void test() {
        Dish dish = new Dish("锅包肉", 38);
        VoteItem<Dish> dishVoteItem = new VoteItem<Dish>(dish, "support");
        Dish dish2 = new Dish("gbr", 39);
        VoteItem<Dish> voteItem = new VoteItem<>(dish2, "oppose");
        HashSet<VoteItem<Dish>> voteItems = new HashSet<>();
        voteItems.add(dishVoteItem);
        voteItems.add(voteItem);
        Vote<Dish> dishVote = new Vote<>(voteItems);

        assertEquals(dishVote.getVoteItems(),voteItems);

        assert dishVote.candidateIncluded(dish);
        assert dishVote.candidateIncluded(dish2);



    }

}
