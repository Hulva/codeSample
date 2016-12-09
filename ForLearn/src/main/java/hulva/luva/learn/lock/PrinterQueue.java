/**
 * 
 */
package hulva.luva.learn.lock;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年12月7日
 *
 */
public class PrinterQueue {
	private final Lock queueLock = new ReentrantLock();

	public void printJob(Object document) {
		queueLock.lock();
		try {
			Long duration = (long) (Math.random() * 10000);
			System.out.println(Thread.currentThread().getName()
					+ ": PrintQueue: Printing a Job during "
					+ (duration / 1000) + " seconds :: Time - " + new Date());
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.printf("%s: The document has been printed\n", Thread
					.currentThread().getName());
			queueLock.unlock();
		}
	}
}
