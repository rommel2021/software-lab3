package pattern;

import auxiliary.Proposal;
import auxiliary.Voter;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BusinessStatistics implements StatisticsStrategy<Proposal>{

    @Override
    public double totalScore(Map<Proposal, Double> statistics, Set<Vote<Proposal>> votes,
                             VoteType voteType, Map<Voter,Double> voters) {

        for(Vote<Proposal> vote:votes){//遍历整个votes，得到的是每一个投票者对所有候选者的选项
            for(VoteItem<Proposal> vi:vote.getVoteItems()){//得到每一个投票项
                double n=voteType.getScoreByOption(vi.getVoteValue())*voters.get(vote.getVoter());//得到投票项的分值
                Proposal p=vi.getCandidate();
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
