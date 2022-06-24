package poll;

import auxiliary.Dish;
import auxiliary.Person;

public class DinnerOrder extends GeneralPollImpl<Dish> implements Poll<Dish> {
	//TODO
    // Rep Invariants
    // GeneralPollImpl的RI
    // 被选中的dish数量大于等于投票者数量（n），小于等于n+5，小于等于菜品总数量
    // Abstract Function
    // 没有新增的属性，所以和父类的AF一样
    // Safety from Rep Exposure
    // GeneralPollImpl所满足的所有防止表示泄露的手段

    public void checkRep(){
        super.checkRep();

    }

    @Override
    public String result() {

        String result="排名如下：\n";
        for(Dish p:results.keySet()){
            result+=p.getName()+" "+results.get(p)+"\n";
        }
        return result;
    }
}
