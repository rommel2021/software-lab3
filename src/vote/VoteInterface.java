package vote;

import java.util.Set;

public interface VoteInterface<C> {
    Set<VoteItem<C>> getVoteItems();

    boolean candidateIncluded(C candidate);


}
