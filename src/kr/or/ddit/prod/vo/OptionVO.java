package kr.or.ddit.prod.vo;

public class OptionVO {
	private String option_id;
	private String option_name;
	private String option_value;
	
	public String getOption_id() {
		return option_id;
	}
	public void setOption_id(String option_id) {
		this.option_id = option_id;
	}
	public String getOption_name() {
		return option_name;
	}
	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}
	public String getOption_value() {
		return option_value;
	}
	public void setOption_value(String option_value) {
		this.option_value = option_value;
	}
	@Override
	public String toString() {
		return "OptionVO [option_id=" + option_id + ", option_name=" + option_name + ", option_value=" + option_value
				+ "]";
	}
	
	
	
}
