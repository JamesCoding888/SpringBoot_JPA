package entity.jpql.where;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "employee_where_literal")
public class EmployeeWhereLiteral {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(name = "salary")
	private Integer salary;
	
	@Column(name = "local_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Column(name = "valid")
	private Boolean valid;	
	
	public EmployeeWhereLiteral() {
		
	}

	
	public EmployeeWhereLiteral(String name, Role role, Integer salary, Date date, Boolean valid) {
		this.name = name;
		this.role = role;
		this.salary = salary;
		this.date = date;
		this.valid = valid;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public Integer getSalary() {
		return salary;
	}


	public void setSalary(Integer salary) {
		this.salary = salary;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Boolean isValid() {
		return valid;
	}


	public void setValid(Boolean valid) {
		this.valid = valid;
	}


	public enum Role {
		IT,
		ADMIN,
		SALE;
	}

	// SELECT DISTINCT 查詢，用來取得成員不重複的集合物件，因此成員類別必須 override hashCode() and equals() method
	@Override
	public int hashCode() {
		return Objects.hash(date, id, name, role, salary, valid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeWhereLiteral other = (EmployeeWhereLiteral) obj;
		return Objects.equals(date, other.date) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& role == other.role && Objects.equals(salary, other.salary) && Objects.equals(valid, other.valid);
	}


	
	

	
	
	
}
