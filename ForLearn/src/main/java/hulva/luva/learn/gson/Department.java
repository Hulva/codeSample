/**
 * 
 */
package hulva.luva.learn.gson;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年12月6日
 *
 */
public class Department {

	private String deptName;

	public Department(String deptName) {
		super();
		this.deptName = deptName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "Department [deptName=" + deptName + "]";
	}

}
