/**
 * 
 */
package hulva.luva.learn.lock;

/**
 * @ClassName
 * @Description This class represents an independent printing which could be
 *              submitted to printer. This class implements Runnable interface,
 *              so that printer can execute it when it's turn come.
 * @author Hulva Luva.H
 * @date 2016年12月7日
 *
 */
public class PrintingJob implements Runnable {
	private PrinterQueue printerQueue;

	public PrintingJob(PrinterQueue printerQueue) {
		this.printerQueue = printerQueue;
	}

	@Override
	public void run() {
		System.out.printf("%s: Going to print a document\n", Thread
				.currentThread().getName());
		printerQueue.printJob(new Object());
	}

}
