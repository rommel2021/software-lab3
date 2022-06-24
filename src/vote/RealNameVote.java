package vote;

import auxiliary.Voter;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//immutable
public class RealNameVote<C> extends Vote<C>{
	
	//Õ∂∆±»À
	private Voter voter;
	

	public RealNameVote(HashSet<VoteItem<C>> candidates,Voter v){
		super(candidates);
		this.voter=v;
	}
	
	public Voter getVoter() {
		return this.voter;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		RealNameVote<?> that = (RealNameVote<?>) o;
		return Objects.equals(voter, that.voter);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), voter);
	}
}
