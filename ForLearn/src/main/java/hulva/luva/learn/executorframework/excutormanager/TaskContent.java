/**
 * 
 */
package hulva.luva.learn.executorframework.excutormanager;

/**
 * @author hulva
 *
 */
public class TaskContent {
	private String taskName;
	private String whatever;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getWhatever() {
		return whatever;
	}

	public void setWhatever(String whatever) {
		this.whatever = whatever;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taskName == null) ? 0 : taskName.hashCode());
		result = prime * result + ((whatever == null) ? 0 : whatever.hashCode());
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
		TaskContent other = (TaskContent) obj;
		if (taskName == null) {
			if (other.taskName != null)
				return false;
		} else if (!taskName.equals(other.taskName))
			return false;
		if (whatever == null) {
			if (other.whatever != null)
				return false;
		} else if (!whatever.equals(other.whatever))
			return false;
		return true;
	}

}
