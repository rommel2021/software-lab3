package pattern;

import java.util.Map;

public interface SelectionStrategy<C> {

	// TODO
    void select(Map<C,Double> statistics,Map<C,Double> results,int quantity);
}
