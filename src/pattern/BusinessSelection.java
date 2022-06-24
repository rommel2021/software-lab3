package pattern;

import auxiliary.Dish;
import auxiliary.Proposal;

import java.util.ArrayList;
import java.util.Map;

public class BusinessSelection implements SelectionStrategy<Proposal>{

    @Override
    public void select(Map<Proposal, Double> statistics, Map<Proposal, Double> results,int quantity) {
        //�����ʵ����ֻ��Ҫ�ҵ�statistics��������
        ArrayList<Proposal> proposals = new ArrayList<>(statistics.keySet());
        ArrayList<Double> scores = new ArrayList<>(statistics.values());
        for (int i = 0; i < proposals.size()-1; i++) {//ð��������һ���Ӵ�С�ķ�ֵ���飬�Ͷ�Ӧ�Ĳ�����
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
            if(i>=quantity && scores.get(i)!=scores.get(i-1))//����ﵽquantity����û���ظ��÷ֲ���ֹͣѭ��
                break;
            results.put(proposals.get(i), (double)i+1);

        }
    }
}
