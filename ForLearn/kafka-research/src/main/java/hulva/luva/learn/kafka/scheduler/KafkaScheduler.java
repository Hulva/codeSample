package hulva.luva.learn.kafka.scheduler;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import hulva.luva.learn.kafka.common.Utils;
import hulva.luva.learn.kafka.log.Logging;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年4月15日
 * @description
 *
 */
public class KafkaScheduler extends Logging implements Scheduler {

    private ScheduledThreadPoolExecutor executor = null;
    private AtomicInteger schedulerThreadId = new AtomicInteger(0);

    private int threads;
    private String threadNamePrefix;
    private boolean daemon;

    public KafkaScheduler(int _threads, String _threadNamePrefix, boolean _daemon) {
        this.threads = _threads;
        this.threadNamePrefix = _threadNamePrefix;
        this.daemon = _daemon;
    }

    @Override
    public synchronized void startup() {
        debug("Initializing task scheduler.");
        if (isStarted())
            throw new IllegalStateException("This scheduler has already been started!");
        executor = new ScheduledThreadPoolExecutor(threads);
        executor.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
        executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
        executor.setThreadFactory(new ThreadFactory() {

            @Override
            public Thread newThread(Runnable runnable) {
                return Utils.newThread(threadNamePrefix + schedulerThreadId.getAndIncrement(),
                        runnable, daemon);
            }
        });
    }

    @Override
    public void shutdown() {
        debug("Shutting down task scheduler.");
        ScheduledThreadPoolExecutor cachedExecutor = this.executor;
        if (cachedExecutor != null) {
            cachedExecutor.shutdown();
            this.executor = null;
        }
        try {
            cachedExecutor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            error("Scheduler shutdown", e);
        }
    }

    @Override
    public synchronized boolean isStarted() {
        return executor != null;
    }

    @SuppressWarnings("static-access")
    @Override
    public synchronized void schedule(String name, Runnable fun, long delay, long period,
            TimeUnit unit) {
        debug("Scheduling task %s with initial delay %d ms and period %d ms.".format(name,
                TimeUnit.MILLISECONDS.convert(delay, unit),
                TimeUnit.MILLISECONDS.convert(period, unit)));
        ensureRunning();
        if (period >= 0) {
            executor.scheduleAtFixedRate(fun, delay, period, unit);
        } else {
            executor.schedule(fun, delay, unit);
        }
    }

    private void ensureRunning() {
        if (!isStarted())
            throw new IllegalStateException("Kafka scheduler is not running.");
    }

}
