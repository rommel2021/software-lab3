package poll;

public class VoteLegalVisitor<C> implements Visitor<C>{
    private double data;
    @Override
    public void visit(GeneralPollImpl<C> poll) {
        data =  poll.getLegalVotes().size() / poll.getVotes().size();
    }

    @Override
    public double getData() {
        return this.data;
    }
}
