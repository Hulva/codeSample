/**
 * 
 */
package hulva.luva.learn.executorframework.timer;

/**
 * @author hulva
 *
 */
public class Task implements Runnable {

	private final String taskName;
	private final String taskFilePath;

	Task(String name, String path) {
		this.taskName = name;
		this.taskFilePath = path;
	}

	@Override
	public void run() {
		// do task
	}

	public String getTaskFilePath() {
		return taskFilePath;
	}

	public String getTaskName() {
		return taskName;
	}

}
