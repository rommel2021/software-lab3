package pattern;

import auxiliary.Dish;
import auxiliary.Voter;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

import java.util.Map;
import java.util.Set;

public class NewDinnerStatistics implements StatisticsStrategy<Dish>{
    @Override
    public double totalScore(Map<Dish, Double> statistics, Set<Vote<Dish>> votes,
                             VoteType voteType, Map<Voter, Double> voters) {
        for(Vote<Dish> vote:votes){//遍历整个votes，得到的是每一个投票者对所有候选者的选项
            for(VoteItem<Dish> vi:vote.getVoteItems()){//得到每一个投票项
                double n=voteType.getScoreByOption(vi.getVoteValue());//得到投票项的分值
                Dish p=vi.getCandidate();
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
