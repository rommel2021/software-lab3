package pattern;

import auxiliary.Person;
import auxiliary.Proposal;
import auxiliary.Voter;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

import java.util.Map;
import java.util.Set;

public class ElectionStatistics implements StatisticsStrategy<Person>{

    @Override
    public double totalScore(Map<Person, Double> statistics, Set<Vote<Person>> votes,
                             VoteType voteType, Map<Voter, Double> voters) {

        //由于这个是匿名投票，所以不能getVoter，直接*1就可以
        for(Vote<Person> vote:votes){//遍历整个votes，得到的是每一个投票者对所有候选者的选项
            for(VoteItem<Person> vi:vote.getVoteItems()){//得到每一个投票项
                double n=voteType.getScoreByOption(vi.getVoteValue());//得到投票项的分值
                Person p=vi.getCandidate();
                if(statistics==null || statistics.get(p)==null){
                    statistics.put(p,n);
                }
                else
                    statistics.put(p,statistics.get(p)+n);
            }
        }
        return 0;
    }
}
