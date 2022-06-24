package pattern;

import auxiliary.Proposal;

import java.util.Map;

public class BusinessSelection implements SelectionStrategy<Proposal>{

    @Override
    public void select(Map<Proposal, Double> statistics, Map<Proposal, Double> results,int quantity) {
        //在这个实现中只需要找到statistics里面最大的
        double max=0;
        for(Double num:statistics.values()){
            max+=num;
        }
        Proposal maxP=null;
        for(Proposal p: statistics.keySet()){
            maxP=p;
        }
        results.put(maxP,max);
    }
}
