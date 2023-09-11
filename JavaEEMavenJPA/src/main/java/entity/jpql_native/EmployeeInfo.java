package entity.jpql_native;

public class EmployeeInfo {
	
	private String name;
	
	private Integer salary;
	
	public EmployeeInfo(String name, Integer salary) {
		super();
		this.name = name;
		this.salary = salary;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSalary() {
		return salary;
	}
	
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	
	
}
