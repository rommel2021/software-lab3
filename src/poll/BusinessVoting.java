package poll;

import auxiliary.Proposal;

public class BusinessVoting extends GeneralPollImpl<Proposal> implements Poll<Proposal> {


    // Rep Invariants
    // GeneralPollImpl的RI
    // 所有股东的权重之和必须为1
    // 候选者数量必须为1
    // Abstract Function
    // 没有新增的属性，所以和父类的AF一样
    // Safety from Rep Exposure
    // GeneralPollImpl所满足的所有防止表示泄露的手段
	//TODO
    @Override
    public void checkRep(){
        super.checkRep();
        assert quantity==1;
    }

    @Override
    public String result() {
        String str="";
        for(Proposal p:results.keySet()){
            if(statistics.get(p)>0.66){
                str+="结果为："+statistics.get(p)+",通过\n";
            }else{
                str+="结果为："+statistics.get(p)+",未通过\n";
            }
        }
        return str;
    }
}