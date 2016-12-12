/**
 * 
 */
package hulva.luva.learn.concurrency.ScheduledThreadPoolExecutor;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年12月7日
 *
 */
public class ScheduledThreadPoolExecutorExample {

	public static void main(String[] args) {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		Task task1 = new Task("Demo task1");
		Task task2 = new Task("Demo task2");

		System.out.println("The time is: " + new Date());

		// the `schedule()` method receives three parameters:
		// -> The task you want to execute
		// -> The period of time you want the task to wait before its execution
		// -> The unit of the period of time, specified as a constant of the
		// TimeUnit class
		executor.schedule(task1, 5, TimeUnit.SECONDS);
		executor.schedule(task2, 10, TimeUnit.SECONDS);
		
		/*ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Task task1 = new Task ("Demo Task 1");
         
        System.out.println("The time is : " + new Date());
         
        // `scheduledAtFixedRate()` method accepts four parameters:
         * -> the task you want to execute periodically
         * -> the delay of time until the first execution of the task
         * -> the period between two executions
         * -> and the time unit of the second and third parameters 
        ScheduledFuture<?> result = executor.scheduleAtFixedRate(task1, 2, 5, TimeUnit.SECONDS);*/

		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
