package vote;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;




class VoteTypeTest {

    // test strategy
    //创建一个新的VoteType,输入一些字符串检查合法性,
    // 再输入一个合法字符串，检查获得的分数是否合法
    // 最后创建另一个表项一样的map，检查是否满足equals

    @Test
    void test() throws Exception {
        HashMap<String, Integer> options = new HashMap<>();
        options.put("oppose", -1);
        options.put("support", 2);
        VoteType voteType = new VoteType(options);
        assertTrue(voteType.checkLegality("oppose"));
        assertFalse(voteType.checkLegality("hhhh"));

        assertEquals(2, voteType.getScoreByOption("support"));
        assertEquals(-1, voteType.getScoreByOption("oppose"));

        HashMap<String, Integer> map = new HashMap<>();
        map.put("oppose", -1);
        map.put("support", 2);
		VoteType voteType1 = new VoteType(map);
		assertEquals(voteType1,voteType);

        VoteType voteType2 = new VoteType("“喜欢”(2)|“不喜欢”(0)|“无所谓”(1)");
        //测试含有负数的投票选项
        VoteType voteType4 = new VoteType("“喜欢”(2)|“不喜欢”(0)|“无所谓”(-2)");
        //测试不含分值项的
        VoteType voteType5 = new VoteType("“喜欢”|“不喜欢”|“无所谓”");
        //测试含有浮点数选项的投票选项
        try{
            VoteType voteType3 = new VoteType("“喜欢”(2.5)|“不喜欢”(0)|“无所谓”(1)");
        }catch(Exception e){
            System.out.println("捕获分值不为整数的异常");
        }
        try{
            VoteType voteType3 = new VoteType("“喜 欢”(2.5)|“不喜欢”(0)|“无所谓”(1)");
        }catch(Exception e){
            System.out.println("捕获出现空格引发的的异常");
        }

    }

}
