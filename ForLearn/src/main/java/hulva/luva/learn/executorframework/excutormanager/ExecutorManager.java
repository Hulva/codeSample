/**
 * 
 */
package hulva.luva.learn.executorframework.excutormanager;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author hulva
 *
 */
public class ExecutorManager {

	private final static List<TaskContent> tasks = new LinkedList<TaskContent>();

	private static ScheduledExecutorService pool;
	private static int latestExecuteIndex = 0;
	private static Timer timer = null;

	private static int frequency = 10 * 1000;

	public static boolean exist(String key) {
		return tasks.contains(key);
	}

	public static void addTask(TaskContent taskContent) throws JSONException {
		// String path = "taskFolder/" + taskName + ".task";
		// path could generated from taskName and taskFolder from
		// application.properties
		tasks.add(taskContent);
		timer.cancel();
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				TaskContent currentExecuteTaskContent = tasks.get(latestExecuteIndex);
				// do task. Let another thread to it?
				/**
				 * Why using multi-thread here? Cause, when task number become
				 * large, that task can not be done within frequency /
				 * tasks.size(). Then next task will be delayed.
				 */
				class TaskRunner implements Runnable {

					private TaskContent taskContent;

					public TaskRunner(TaskContent currentExecuteTaskContent) {
						this.taskContent = currentExecuteTaskContent;
					}

					@Override
					public void run() {
						// TODO do task

					}

				}
				pool.execute(new TaskRunner(currentExecuteTaskContent));
				if (latestExecuteIndex < tasks.size()) {
					latestExecuteIndex++;
				} else {
					latestExecuteIndex = 0;
				}
			}

		}, 0, frequency / tasks.size());
	}

	protected static JSONObject loadTaskFile(String taskFilePath) {
		// TODO load task content from task file
		return null;
	}

	ExecutorManager(int poolSize) {
		pool = Executors.newScheduledThreadPool(poolSize);
	}

	public void addTask() {

	}

	private class MyExecutor implements Runnable {
		private int counter = 0;
		private final int period;
		private final String taskFilePath;
		private boolean exit;

		MyExecutor(int period, String taskFilePath) {
			this.exit = false;
			this.period = period;
			this.taskFilePath = taskFilePath;
		}

		@Override
		public void run() {
			while (!exit) {
				if (counter == 0) {
					// TODO do the job
				}
			}
		}

	}
}
