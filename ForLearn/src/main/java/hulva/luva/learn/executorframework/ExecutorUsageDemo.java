/**
 * 
 */
package hulva.luva.learn.executorframework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年12月6日
 *
 */
public class ExecutorUsageDemo {

	private static ExecutorService executor = null;
	private static volatile Future<?> taskOneResults = null;
	private static volatile Future<?> taskTwoResults = null;

	public static void main(String[] args) {
		executor = Executors.newFixedThreadPool(2);
		try {
			while (true) {
				checkTasks();
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void checkTasks() {
		if (taskOneResults == null || taskOneResults.isDone()
				|| taskOneResults.isCancelled()) {
			taskOneResults = executor
					.submit((new ExecutorUsageDemo()).new RunnableOne());
		}

		if (taskTwoResults == null || taskTwoResults.isDone()
				|| taskTwoResults.isCancelled()) {
			taskTwoResults = executor
					.submit((new ExecutorUsageDemo()).new RunnableTwo());
		}
	}

	/**
	 * @ClassName
	 * @Description
	 * @author Hulva Luva.H
	 * @date 2016年12月6日
	 *
	 */
	class RunnableOne implements Runnable {

		@Override
		public void run() {
			while (true) {
				System.out.println("Executing task one");
				try {
					Thread.sleep(1000);
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * @ClassName
	 * @Description
	 * @author Hulva Luva.H
	 * @date 2016年12月6日
	 *
	 */
	public class RunnableTwo implements Runnable {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			while (true) {
				System.out.println("Executing task two");
				try {
					Thread.sleep(1000);
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}

	}
}
