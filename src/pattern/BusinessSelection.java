package pattern;

import auxiliary.Dish;
import auxiliary.Proposal;

import java.util.ArrayList;
import java.util.Map;

public class BusinessSelection implements SelectionStrategy<Proposal>{

    @Override
    public void select(Map<Proposal, Double> statistics, Map<Proposal, Double> results,int quantity) {
        //在这个实现中只需要找到statistics里面最大的
        ArrayList<Proposal> proposals = new ArrayList<>(statistics.keySet());
        ArrayList<Double> scores = new ArrayList<>(statistics.values());
        for (int i = 0; i < proposals.size()-1; i++) {//冒泡排序获得一个从大到小的分值数组，和对应的菜数组
            for (int j = i+1; j < proposals.size(); j++) {
                if(scores.get(i)<scores.get(j)){
                    Proposal temp=proposals.get(j);
                    proposals.set(j,proposals.get(i));
                    proposals.set(i,temp);

                    double tem=scores.get(j);
                    scores.set(j,scores.get(i));
                    scores.set(i,tem);
                }
            }
        }
        for(int i=0;i< proposals.size();i++){
            if(i>=quantity && scores.get(i)!=scores.get(i-1))//必须达到quantity并且没有重复得分才能停止循环
                break;
            results.put(proposals.get(i), (double)i+1);

        }
    }
}
