package pattern;

import auxiliary.Person;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NewElectionSelection implements SelectionStrategy<Person>{

    Map<Person, Double> rejectStatistics = new HashMap<>();

    //统计反对选票
    public NewElectionSelection(Set<Vote<Person>> legalVotes){
        for(Vote<Person> vote:legalVotes){
            for(VoteItem<Person> vi:vote.getVoteItems()){
                if(vi.getVoteValue().equals("反对") && rejectStatistics.get(vi.getCandidate())==null){
                    rejectStatistics.put(vi.getCandidate(),1.0);
                }else if(vi.getVoteValue().equals("反对") && rejectStatistics.get(vi.getCandidate())!=null){
                    rejectStatistics.put(vi.getCandidate(),rejectStatistics.get(vi.getCandidate())+1.0);
                }
            }
        }

    }
    @Override
    public void select(Map<Person, Double> statistics, Map<Person, Double> results, int quantity) {
        ArrayList<Person> persons = new ArrayList<>(statistics.keySet());
        ArrayList<Double> scores = new ArrayList<>(statistics.values());
        for (int i = 0; i < persons.size()-1; i++) {
            for (int j = i+1; j < persons.size(); j++) {
                if(scores.get(i)<scores.get(j)){
                    Person temp=persons.get(j);
                    persons.set(j,persons.get(i));
                    persons.set(i,temp);

                    double tem=scores.get(j);
                    scores.set(j,scores.get(i));
                    scores.set(i,tem);
                }
            }
        }
        for(int i=0;i< persons.size();i++){
            if(i>=quantity && scores.get(i)==scores.get(i-1)){
                if(rejectStatistics.get(persons.get(i)) > rejectStatistics.get(persons.get(i-1)))
                    results.put(persons.get(i+1), (double)i+1);
                else if(rejectStatistics.get(persons.get(i)) < rejectStatistics.get(persons.get(i-1)))
                    results.put(persons.get(i),(double)i+1);
                break;
            }

            results.put(persons.get(i), (double)i+1);
        }
    }
}
