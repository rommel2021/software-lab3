package poll;

import auxiliary.Person;
import vote.Vote;
import vote.VoteItem;

import java.util.ArrayList;
import java.util.Collection;

public class Election extends GeneralPollImpl<Person> implements Poll<Person> {

    // Rep Invariants
    // GeneralPollImpl��RI
    // ����voters��value��������ͬ��
    // ͶƱ�˵��޳�Ʊ�������ܳ���quantity
    // Abstract Function
    // û�����������ԣ����Ժ͸����AFһ��
    // Safety from Rep Exposure
    // GeneralPollImpl����������з�ֹ��ʾй¶���ֶ�

    public void checkRep(){
        super.checkRep();
        ArrayList list = new ArrayList<>( voters.values());
        for(int i=1;i<list.size();i++){
            assert list.get(i)==list.get(i-1);
        }
        ArrayList<Vote> vs=new ArrayList(votes);
        for(Vote<VoteItem> v:vs){
            for(VoteItem vi:v.getVoteItems()){
                int n=0;
                if(vi.getVoteValue().equals("֧��"))
                    n++;
                assert n<=quantity;
            }
        }
    }

    @Override
    public void addVote(Vote<Person> vote) throws Exception {
        //Ҫ���һ��ѡƱ��֧�ֵ��Ƿ����quantity
        int num=0;
        for(VoteItem<Person> vi:vote.getVoteItems()){
            if(vi.getVoteValue().equals("֧��"))
                num++;
        }
        if(num>quantity)
            return;
        super.addVote(vote);
    }

    @Override
    public String result() {
        String result="�������£�\n";
        for(Person p:results.keySet()){
            result+=p.getName()+" "+results.get(p)+"\n";
        }
        return result;
    }
}
