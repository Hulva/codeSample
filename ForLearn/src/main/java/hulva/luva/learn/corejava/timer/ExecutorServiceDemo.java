/**
 * 
 */
package hulva.luva.learn.corejava.timer;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author lh10
 *
 */
public class ExecutorServiceDemo {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		TimerTask repeatedTask = new TimerTask() {

			@Override
			public void run() {
				System.out.println("Task performed on " + new Date());
			}
		};
		ScheduledExecutorService executor = Executors
				.newSingleThreadScheduledExecutor();
		long delay = 1000L;
		long period = 1000L;
		executor.scheduleAtFixedRate(repeatedTask, delay, period,
				TimeUnit.MILLISECONDS);
		Thread.sleep(delay + period * 3);
		executor.shutdown();
	}
}
