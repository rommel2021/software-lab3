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
        for(Vote<Dish> vote:votes){//��������votes���õ�����ÿһ��ͶƱ�߶����к�ѡ�ߵ�ѡ��
            for(VoteItem<Dish> vi:vote.getVoteItems()){//�õ�ÿһ��ͶƱ��
                double n=voteType.getScoreByOption(vi.getVoteValue());//�õ�ͶƱ��ķ�ֵ
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
