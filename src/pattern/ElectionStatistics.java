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

        //�������������ͶƱ�����Բ���getVoter��ֱ��*1�Ϳ���
        for(Vote<Person> vote:votes){//��������votes���õ�����ÿһ��ͶƱ�߶����к�ѡ�ߵ�ѡ��
            for(VoteItem<Person> vi:vote.getVoteItems()){//�õ�ÿһ��ͶƱ��
                double n=voteType.getScoreByOption(vi.getVoteValue());//�õ�ͶƱ��ķ�ֵ
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
