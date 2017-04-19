package hulva.luva.learn.kafka.scheduler;

import java.util.concurrent.TimeUnit;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年4月15日
 * @description
 *
 */
public interface Scheduler {

    /**
     * Initialize this scheduler so it is ready to accept scheduling of tasks
     * @return 
     */
    void startup();

    /**
     * Shutdown this scheduler. When this method is complete no more executions of background tasks
     * will occur. This includes tasks scheduled with a delayed execution.
     */
    void shutdown();

    /**
     * Check if the scheduler has been started
     * 
     * @return
     */
    boolean isStarted();

    /**
     * Schedule a task
     * 
     * @param name The name of this task
     * @param fun
     * @param delay
     * @param period
     * @param unit
     */
    void schedule(String name, Runnable fun, long delay, long period, TimeUnit unit);
}
