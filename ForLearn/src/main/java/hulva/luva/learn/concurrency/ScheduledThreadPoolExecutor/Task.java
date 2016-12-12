/**
 * 
 */
package hulva.luva.learn.concurrency.ScheduledThreadPoolExecutor;

import java.util.Date;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年12月7日
 *
 */
public class Task implements Runnable {

	private String name;

	public Task(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void run() {
		try {
			System.out.println("Doing a task during: " + name + " - Time - "
					+ new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
