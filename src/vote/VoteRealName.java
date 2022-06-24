package vote;

import auxiliary.Voter;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class VoteRealName<C> extends VoteDecorator{

    private Voter voter;

    public VoteRealName(HashSet<VoteItem<C>> candidates, Voter v){
        voteItems=candidates;
        this.date=Calendar.getInstance();
        this.voter=v;

    }


}
