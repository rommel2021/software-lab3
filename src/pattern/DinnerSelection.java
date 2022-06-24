package pattern;

import auxiliary.Dish;
import auxiliary.Person;

import java.util.ArrayList;
import java.util.Map;

public class DinnerSelection implements SelectionStrategy<Dish>{
    @Override
    public void select(Map<Dish, Double> statistics, Map<Dish, Double> results, int quantity) {
        ArrayList<Dish> dishes = new ArrayList<>(statistics.keySet());
        ArrayList<Double> scores = new ArrayList<>(statistics.values());
        for (int i = 0; i < dishes.size()-1; i++) {//冒泡排序获得一个从大到小的分值数组，和对应的菜数组
            for (int j = i+1; j < dishes.size(); j++) {
                if(scores.get(i)<scores.get(j)){
                    Dish temp=dishes.get(j);
                    dishes.set(j,dishes.get(i));
                    dishes.set(i,temp);

                    double tem=scores.get(j);
                    scores.set(j,scores.get(i));
                    scores.set(i,tem);
                }
            }
        }
        for(int i=0;i< dishes.size();i++){
            if(i>=quantity && scores.get(i)!=scores.get(i-1))//必须达到quantity并且没有重复得分才能停止循环
                break;
            results.put(dishes.get(i), (double)i+1);

        }
    }
}
