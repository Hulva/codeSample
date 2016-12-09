/**
 * 
 */
package hulva.luva.learn.lock;

/**
 * @ClassName
 * @Description The key to the example is in the printJob() method of the
 *              PrinterQueue class. When we want to implement a critical section
 *              using locks and guarantee that only one execution thread runs a
 *              block of code, we have to create a ReentrantLock object. At the
 *              beginning of the critical section, we have to get the control of
 *              the lock using the lock() method.
 * 
 *              At the end of the critical section, we have to use the unlock()
 *              method to free the control of the lock and allow the other
 *              threads to run this critical section. If you don’t call the
 *              unlock() method at the end of the critical section, the other
 *              threads that are waiting for that block will be waiting forever,
 *              causing a deadlock situation. If you use try-catch blocks in
 *              your critical section, don’t forget to put the sentence
 *              containing the unlock() method inside the finally section.
 * @author Hulva Luva.H
 * @date 2016年12月7日
 *
 */
public class LockDemo {

	public static void main(String[] args) {
		PrinterQueue printerQueue = new PrinterQueue();
		Thread thread[] = new Thread[10];
		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread(new PrintingJob(printerQueue), "Thread " + i);
		}
		for (int i = 0; i < 10; i++) {
			thread[i].start();
		}
	}

}
