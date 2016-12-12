/**
 * 
 */
package hulva.luva.learn.executorframework;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年12月6日
 *
 */
public class MyTask {
	public class TaskOne implements Runnable {
		@Override
		public void run() {
			System.out.println("Executing Task One");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public class TaskTwo implements Runnable {
		@Override
		public void run() {
			System.out.println("Executing Task Two");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public class TaskThree implements Runnable {
		@Override
		public void run() {
			System.out.println("Executing Task Three");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
