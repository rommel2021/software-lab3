package pattern;

import auxiliary.Voter;
import vote.Vote;
import vote.VoteType;

import java.util.Map;
import java.util.Set;

public interface StatisticsStrategy<C> {
	// TODO
    /**
     * 获得候选人总分数
     *
     * @param
     */
    double totalScore(Map<C,Double> scores, Set<Vote<C>> votes,
                      VoteType voteType,Map<Voter,Double> Voters);
}
