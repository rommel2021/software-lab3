package poll;

import auxiliary.Proposal;

public class BusinessVoting extends GeneralPollImpl<Proposal> implements Poll<Proposal> {


    // Rep Invariants
    // GeneralPollImpl��RI
    // ���йɶ���Ȩ��֮�ͱ���Ϊ1
    // ��ѡ����������Ϊ1
    // Abstract Function
    // û�����������ԣ����Ժ͸����AFһ��
    // Safety from Rep Exposure
    // GeneralPollImpl����������з�ֹ��ʾй¶���ֶ�
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
                str+="���Ϊ��"+statistics.get(p)+",ͨ��\n";
            }else{
                str+="���Ϊ��"+statistics.get(p)+",δͨ��\n";
            }
        }
        return str;
    }
}