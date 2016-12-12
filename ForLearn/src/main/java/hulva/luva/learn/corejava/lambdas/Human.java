/**
 * 
 */
package hulva.luva.learn.corejava.lambdas;

/**
 * @author lh10
 *
 */
public class Human {
	private String name;
	private int age;
	
	public Human() {
		super();
	}

	public Human(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Human other = (Human) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public static int compareByNameThenAge(Human lhs, Human rhs) {
	    if (lhs.name.equals(rhs.name)) {
	        return lhs.age - rhs.age;
	    } else {
	        return lhs.name.compareTo(rhs.name);
	    }
	}

	@Override
	public String toString() {
		return "Human [name=" + name + ", age=" + age + "]";
	}
	
}
