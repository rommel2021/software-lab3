package poll;

import auxiliary.Dish;
import auxiliary.Person;

public class DinnerOrder extends GeneralPollImpl<Dish> implements Poll<Dish> {
	//TODO
    // Rep Invariants
    // GeneralPollImpl��RI
    // ��ѡ�е�dish�������ڵ���ͶƱ��������n����С�ڵ���n+5��С�ڵ��ڲ�Ʒ������
    // Abstract Function
    // û�����������ԣ����Ժ͸����AFһ��
    // Safety from Rep Exposure
    // GeneralPollImpl����������з�ֹ��ʾй¶���ֶ�

    public void checkRep(){
        super.checkRep();

    }

    @Override
    public String result() {

        String result="�������£�\n";
        for(Dish p:results.keySet()){
            result+=p.getName()+" "+results.get(p)+"\n";
        }
        return result;
    }
}
