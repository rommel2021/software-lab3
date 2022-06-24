package vote;

import static org.junit.jupiter.api.Assertions.*;

import auxiliary.Dish;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

class VoteTest {

    // test strategy
    // �ȴ���һ����������ѡ�ߵ�Vote
    // �жϻ�õ�VoteItems���Լ���voteItems�Ƿ���ͬ
    // ���ж��Լ�������dish�Ƿ������Vote��

    @Test
    void test() {
        Dish dish = new Dish("������", 38);
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
