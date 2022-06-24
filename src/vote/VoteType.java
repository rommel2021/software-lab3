package vote;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//immutable
public class VoteType {

	// keyΪѡ������valueΪѡ������Ӧ�ķ���
	private Map<String, Integer> options = new HashMap<>();

	// Rep Invariants
	// һ����Ϊoptions��Map��¼ѡ�����ͷ����Ķ�Ӧ��ϵ��ӳ�䵽������ѡ��
	// Abstract Function
	// Map��key��Ϊnull��valueΪ�����������ɸ���Ϊ�㣩
	// Safety from Rep Exposure
	// options����Ϊprivate�ģ�����û�л�ȡMap�ķ���������й¶

	private void checkRep() {
		// TODO
		for(String name:options.keySet()){
			assert name!=null;
		}

	}

	/**
	 * ����һ��ͶƱ���Ͷ���
	 * 
	 * ����������Ƹ÷��������õĲ���
	 */
	public VoteType(){

	}
	public VoteType(Map<String,Integer> options) {
		// TODO
		this.options=options;
		checkRep();
	}

	/**
	 * ���������ض��﷨������ַ���������һ��ͶƱ���Ͷ���
	 * 
	 * @param regex ��ѭ�ض��﷨�ġ�����ͶƱ������Ϣ���ַ�����������12�ٿ��ǣ�
	 */
	public VoteType(String regex) throws Exception {
		//��ϲ����(2)|����ϲ����(0)|������ν��(1)
		//��֧�֡�|�����ԡ�|����Ȩ��
		//(\��[\u4e00-\u9fa5]+\��(\(-?[0-9]+\))?\|)+\��[\u4e00-\u9fa5]+\��(\(-?[0-9]+\))?
		// ?(\(-?[0-9]+\))
		Pattern str=Pattern.compile("(\\��[\\u4e00-\\u9fa5]+\\��(\\(-?[0-9]+\\))?\\|)+\\��[\\u4e00-\\u9fa5]+\\��(\\(-?[0-9]+\\))?");
		Matcher m=str.matcher(regex);
		if(m.matches()){
			System.out.println();
		}else{
			throw new Exception("����ı��ʽ��ƥ��");
		}

	}



	/** done
	 * �ж�һ��ͶƱѡ���Ƿ�Ϸ�������Poll�ж�ѡƱ�ĺϷ��Լ�飩
	 * 
	 * ���磬ͶƱ�˸�����ͶƱѡ���ǡ�Strongly reject������options�в��������ѡ�������ǷǷ���
	 * 
	 * ���ܸĶ��÷����Ĳ���
	 * 
	 * @param option һ��ͶƱѡ��
	 * @return �Ϸ���true������false
	 */
	public boolean checkLegality(String option) {
		// TODO
		for(String str: options.keySet()){
			if(str.equals(option))
				return true;
		}
		return false;
	}

	/** done
	 * ����һ��ͶƱѡ��õ����Ӧ�ķ���
	 * 
	 * ���磬ͶƱ�˸�����ͶƱѡ���ǡ�֧�֡�����ѯ�õ����Ӧ�ķ�����1
	 * 
	 * ���ܸĶ��÷����Ĳ���
	 * 
	 * @param option һ��ͶƱ��ȡֵ
	 * @return ��ѡ������Ӧ�ķ���
	 */
	public int getScoreByOption(String option) {
		// TODO
		for(String str: options.keySet()){
			if(str.equals(option))
				return options.get(option);
		}
		return 0;
	}

	public Map<String,Integer> getOptions(){
		return new HashMap<String,Integer>(options);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		VoteType voteType = (VoteType) o;
		return Objects.equals(options, voteType.options);
	}

	@Override
	public int hashCode() {
		return Objects.hash(options);
	}
}
