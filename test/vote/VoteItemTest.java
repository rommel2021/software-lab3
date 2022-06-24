package vote;

import static org.junit.jupiter.api.Assertions.*;

import auxiliary.Dish;
import org.junit.jupiter.api.Test;

class VoteItemTest {

    // test strategy
    // 创建一个候选者为Dish的VoteItem对象
    // 返回候选者和投票选项，检查和预期是否相同
    // 创建另一投票项和选项一致的对象，检查是否相同

    @Test
    void test() {
        Dish dish1 = new Dish("葱烧海参", 208);
        VoteItem<Dish> item = new VoteItem<Dish>(dish1, "support");
        assertEquals("support", item.getVoteValue());
        assertEquals(dish1, item.getCandidate());

        Dish dish0 = new Dish("葱烧海参", 208);
        assertEquals(dish0, dish1);
    }

}
