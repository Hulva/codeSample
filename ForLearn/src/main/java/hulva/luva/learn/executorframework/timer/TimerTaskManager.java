/**
 * 
 */
package hulva.luva.learn.executorframework.timer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author hulva
 *
 */
public class TimerTaskManager {

	// private final static Map<String, String> tasks = new HashMap<String,
	// String>();
	private final static List<String> tasks = new LinkedList<String>();

	private static int latestExecuteIndex = 0;
	private static Timer timer = null;

	private static int frequency = 10 * 1000;

	public static boolean exist(String key) {
		return tasks.contains(key);
	}

	public static void addTask(JSONObject taskContent) throws JSONException {
		String taskName = taskContent.getString("taskName");
		String path = "taskFolder/" + taskName + ".task";
		tasks.add(taskName + "," + path);
		timer.cancel();
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				String taskName_taskFilePath = tasks.get(latestExecuteIndex);
				String[] arr_taskName_taskFilePath = taskName_taskFilePath.split(",");
				JSONObject taskContent = loadTaskFile(arr_taskName_taskFilePath[1]);
				// do task. Let another thread to it?
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

	private class MyTimerTask extends TimerTask {

		public void reloadTasks() {

		}

		@Override
		public void run() {

		}

	}

	public static void main(String[] args) throws InterruptedException {
		List<String> test = new LinkedList<String>();
		test.add("hello,world");
		test.add("he,world");
		test.add("he,word");
		if (!test.contains("hello,world")) {
			test.add("hello,world");
		}
		System.out.println(test);
		System.out.println(test.get(2));
	}

}
