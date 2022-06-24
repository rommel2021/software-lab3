package pattern;

import auxiliary.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ElectionSelection implements SelectionStrategy<Person> {
    @Override
    public void select(Map<Person, Double> statistics, Map<Person, Double> results, int quantity) {
        //这个实现需要解决数量的问题
        //和之前的business的相比，这个需要对每一个key进行排序
        //说statistics是非空的
        ArrayList<Person> persons = new ArrayList<>(statistics.keySet());
        ArrayList<Double> scores = new ArrayList<>(statistics.values());
        for (int i = 0; i < persons.size()-1; i++) {
            for (int j = i+1; j < persons.size(); j++) {
                if(scores.get(i)<scores.get(j)){
                    Person temp=persons.get(j);
                    persons.set(j,persons.get(i));
                    persons.set(i,temp);

                    double tem=scores.get(j);
                    scores.set(j,scores.get(i));
                    scores.set(i,tem);
                }
            }
        }
        for(int i=0;i< persons.size();i++){
            if(i>=quantity && scores.get(i)!=scores.get(i-1))
                break;
            results.put(persons.get(i), (double)i+1);
        }
    }
}
