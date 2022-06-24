package vote;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//immutable
public class VoteType {

	// key为选项名、value为选项名对应的分数
	private Map<String, Integer> options = new HashMap<>();

	// Rep Invariants
	// 一个名为options的Map记录选项名和分数的对应关系，映射到数个被选项
	// Abstract Function
	// Map的key不为null，value为整数（可正可负可为零）
	// Safety from Rep Exposure
	// options设置为private的，并且没有获取Map的方法，不会泄露

	private void checkRep() {
		// TODO
		for(String name:options.keySet()){
			assert name!=null;
		}

	}

	/**
	 * 创建一个投票类型对象
	 * 
	 * 可以自行设计该方法所采用的参数
	 */
	public VoteType(){

	}
	public VoteType(Map<String,Integer> options) {
		// TODO
		this.options=options;
		checkRep();
	}

	/**
	 * 根据满足特定语法规则的字符串，创建一个投票类型对象
	 * 
	 * @param regex 遵循特定语法的、包含投票类型信息的字符串（待任务12再考虑）
	 */
	public VoteType(String regex) throws Exception {
		//“喜欢”(2)|“不喜欢”(0)|“无所谓”(1)
		//“支持”|“反对”|“弃权”
		//(\“[\u4e00-\u9fa5]+\”(\(-?[0-9]+\))?\|)+\“[\u4e00-\u9fa5]+\”(\(-?[0-9]+\))?
		// ?(\(-?[0-9]+\))
		Pattern str=Pattern.compile("(\\“[\\u4e00-\\u9fa5]+\\”(\\(-?[0-9]+\\))?\\|)+\\“[\\u4e00-\\u9fa5]+\\”(\\(-?[0-9]+\\))?");
		Matcher m=str.matcher(regex);
		if(m.matches()){
			System.out.println();
		}else{
			throw new Exception("输入的表达式不匹配");
		}

	}



	/** done
	 * 判断一个投票选项是否合法（用于Poll中对选票的合法性检查）
	 * 
	 * 例如，投票人给出的投票选项是“Strongly reject”，但options中不包含这个选项，因此它是非法的
	 * 
	 * 不能改动该方法的参数
	 * 
	 * @param option 一个投票选项
	 * @return 合法则true，否则false
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
	 * 根据一个投票选项，得到其对应的分数
	 * 
	 * 例如，投票人给出的投票选项是“支持”，查询得到其对应的分数是1
	 * 
	 * 不能改动该方法的参数
	 * 
	 * @param option 一个投票项取值
	 * @return 该选项所对应的分数
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
