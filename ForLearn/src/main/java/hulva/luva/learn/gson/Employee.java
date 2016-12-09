/**
 * 
 */
package hulva.luva.learn.gson;

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Since;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年12月6日
 *
 */
public class Employee {
	@Since(1.0)
	private Integer id;
	private String firstName;
	private String lastName;
	private List<String> roles;

	@Since(1.1)
	private Department department;

	@Since(1.2)
	private Date birthDay;

	public Employee() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", roles=" + roles
				+ ", department=" + department + ", birthDay=" + birthDay + "]";
	}

}
