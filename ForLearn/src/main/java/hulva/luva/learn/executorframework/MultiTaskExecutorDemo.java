/**
 * 
 */
package hulva.luva.learn.executorframework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年12月6日
 *
 */
public class MultiTaskExecutorDemo {

	/**
	 * @ClassName
	 * @Description
	 * @author Hulva Luva.H
	 * @date 2016年12月6日
	 *
	 */
	public class RejectedExecutionHandlerImpl implements
			RejectedExecutionHandler {

		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			System.out.println(r.toString() + " : I've been rejected ! ");
		}

	}

	public static void main(String[] args) {
		BlockingQueue<Runnable> worksQueue = new ArrayBlockingQueue<Runnable>(
				10);
		RejectedExecutionHandler rejectionHandler = (new MultiTaskExecutorDemo()).new RejectedExecutionHandlerImpl();
		ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 10,
				TimeUnit.SECONDS, worksQueue, rejectionHandler);

		executor.prestartAllCoreThreads();

		List<Runnable> taskGroup = new ArrayList<Runnable>();
		MyTask myTask = new MyTask();
		taskGroup.add(myTask.new TaskOne());
		taskGroup.add(myTask.new TaskTwo());
		taskGroup.add(myTask.new TaskThree());

		TaskCache tc = new TaskCache(taskGroup);
		worksQueue.add(tc);
		tc.addRunnable(myTask.new TaskOne());
		tc.addRunnable(myTask.new TaskThree());
	}

}
