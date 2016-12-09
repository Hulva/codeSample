/**
 * 
 */
package hulva.luva.learn.executorframework;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年12月6日
 *
 */
public class TaskCache implements Runnable {

	private final List<Runnable> runnables;

	public TaskCache(List<Runnable> runnables) {
		super();
		this.runnables = runnables;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		for (Runnable runnable : runnables) {
			new Thread(runnable).start();
		}
	}

	public List<Runnable> getRunnables() {
		return runnables;
	}

	public void addRunnable(Runnable r) {
		runnables.add(r);
	}

}
