package vote;

import auxiliary.Voter;

import java.util.Calendar;
import java.util.Collections;
import java.util.Set;

public abstract class VoteDecorator<C> implements VoteInterface<C>{
    Set<VoteItem<C>> voteItems;
    Calendar date;
    @Override
    public Set<VoteItem<C>> getVoteItems() {
        return Collections.unmodifiableSet(voteItems);
    }

    @Override
    public boolean candidateIncluded(C candidate) {
        Set set=this.getVoteItems();
        for(Object o:set){
            VoteItem vi=(VoteItem) o;
            if(vi.getCandidate().equals(candidate))
                return true;
        }
        return false;
    }
}
