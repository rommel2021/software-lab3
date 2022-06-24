package vote;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;




class VoteTypeTest {

    // test strategy
    //����һ���µ�VoteType,����һЩ�ַ������Ϸ���,
    // ������һ���Ϸ��ַ���������õķ����Ƿ�Ϸ�
    // ��󴴽���һ������һ����map������Ƿ�����equals

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

        VoteType voteType2 = new VoteType("��ϲ����(2)|����ϲ����(0)|������ν��(1)");
        //���Ժ��и�����ͶƱѡ��
        VoteType voteType4 = new VoteType("��ϲ����(2)|����ϲ����(0)|������ν��(-2)");
        //���Բ�����ֵ���
        VoteType voteType5 = new VoteType("��ϲ����|����ϲ����|������ν��");
        //���Ժ��и�����ѡ���ͶƱѡ��
        try{
            VoteType voteType3 = new VoteType("��ϲ����(2.5)|����ϲ����(0)|������ν��(1)");
        }catch(Exception e){
            System.out.println("�����ֵ��Ϊ�������쳣");
        }
        try{
            VoteType voteType3 = new VoteType("��ϲ ����(2.5)|����ϲ����(0)|������ν��(1)");
        }catch(Exception e){
            System.out.println("������ֿո������ĵ��쳣");
        }

    }

}
