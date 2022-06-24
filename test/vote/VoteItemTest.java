package vote;

import static org.junit.jupiter.api.Assertions.*;

import auxiliary.Dish;
import org.junit.jupiter.api.Test;

class VoteItemTest {

    // test strategy
    // ����һ����ѡ��ΪDish��VoteItem����
    // ���غ�ѡ�ߺ�ͶƱѡ�����Ԥ���Ƿ���ͬ
    // ������һͶƱ���ѡ��һ�µĶ��󣬼���Ƿ���ͬ

    @Test
    void test() {
        Dish dish1 = new Dish("���պ���", 208);
        VoteItem<Dish> item = new VoteItem<Dish>(dish1, "support");
        assertEquals("support", item.getVoteValue());
        assertEquals(dish1, item.getCandidate());

        Dish dish0 = new Dish("���պ���", 208);
        assertEquals(dish0, dish1);
    }

}
