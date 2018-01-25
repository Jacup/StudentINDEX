package mvc.model;

public class Grade {

	private Integer id; 
	private String grade;
	private String weight;
	private String desc;

	
	public Grade(Integer id, String grade, String weight, String desc) {
		this(grade, weight, desc);
		this.id = id;
	}
	
	public Grade(String grade, String weight, String desc) {
		this.grade = grade;
		this.weight = weight;
		this.desc = desc;

	}

	public Integer getId() {
		return id;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public String getWeight() {
		return weight;
	}
	
	public String getDesc() {
		return desc;
	}
}

