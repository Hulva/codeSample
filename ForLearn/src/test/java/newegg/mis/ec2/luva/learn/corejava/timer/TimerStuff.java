/**
 * 
 */
package newegg.mis.ec2.luva.learn.corejava.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

/**
 * @author lh10
 *
 */
public class TimerStuff {

	@Test
	public void givenUsingTimer_whenSchedulingTaskOnce_thenCorrect() {
	    TimerTask task = new TimerTask() {
	        public void run() {
	            System.out.println("Task performed on: " + new Date() + "n" +
	              "Thread's name: " + Thread.currentThread().getName());
	        }
	    };
	    Timer timer = new Timer("Timer");
	     
	    long delay = 1000L;
	    timer.schedule(task, delay);
	}
}
