package poll;

public interface Visitor<C> {
    void visit(GeneralPollImpl<C> poll);

    double getData();
}
