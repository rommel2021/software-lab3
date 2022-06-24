package poll;

import auxiliary.Person;
import vote.Vote;
import vote.VoteItem;

import java.util.ArrayList;
import java.util.Collection;

public class Election extends GeneralPollImpl<Person> implements Poll<Person> {

    // Rep Invariants
    // GeneralPollImpl的RI
    // 所有voters的value必须是相同的
    // 投票人的赞成票数量不能超过quantity
    // Abstract Function
    // 没有新增的属性，所以和父类的AF一样
    // Safety from Rep Exposure
    // GeneralPollImpl所满足的所有防止表示泄露的手段

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
                if(vi.getVoteValue().equals("支持"))
                    n++;
                assert n<=quantity;
            }
        }
    }

    @Override
    public void addVote(Vote<Person> vote) throws Exception {
        //要检查一张选票中支持的是否大于quantity
        int num=0;
        for(VoteItem<Person> vi:vote.getVoteItems()){
            if(vi.getVoteValue().equals("支持"))
                num++;
        }
        if(num>quantity)
            return;
        super.addVote(vote);
    }

    @Override
    public String result() {
        String result="排名如下：\n";
        for(Person p:results.keySet()){
            result+=p.getName()+" "+results.get(p)+"\n";
        }
        return result;
    }
}
