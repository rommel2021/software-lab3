package pattern;

import auxiliary.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ElectionSelection implements SelectionStrategy<Person> {
    @Override
    public void select(Map<Person, Double> statistics, Map<Person, Double> results, int quantity) {
        //���ʵ����Ҫ�������������
        //��֮ǰ��business����ȣ������Ҫ��ÿһ��key��������
        //˵statistics�Ƿǿյ�
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
