/**
 * 
 */
package hulva.luva.learn.corejava.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author lh10
 *
 */
public class TimerStuffMain {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		/** 一次性任务 **/
		/*
		 * TimerTask task = new TimerTask() { public void run() {
		 * System.out.println("Task performed on: " + new Date() + "n" +
		 * "Thread's name: " + Thread.currentThread().getName()); } }; Timer
		 * timer = new Timer("Timer");
		 * 
		 * long delay = 100L; timer.schedule(task, delay);
		 */

		/**
		 * 重复性任务 如果其中一次任务执行被耽误了（gc或者是其他后台活动），将出现在指定时间段内多次任务执行的情况
		 **/
		TimerTask repeatedTask = new TimerTask() {
			public void run() {
				System.out.println("Task performed on " + new Date());
				// cancel();
			}
		};
		Timer timer = new Timer("Timer");

		long delay = 1000L;
		long period = 1000L;
		timer.scheduleAtFixedRate(repeatedTask, delay, period);
		
		//timer.cancel(); 取消Timer
	}

	// 一天执行一次
	public static void schedulingDailyTask() {
		TimerTask repeatedTask = new TimerTask() {
			public void run() {
				System.out.println("Task performed on " + new Date());
			}
		};
		Timer timer = new Timer("Timer");

		long delay = 1000L;
		long period = 1000L * 60L * 60L * 24L;
		timer.scheduleAtFixedRate(repeatedTask, delay, period);
	}

}
