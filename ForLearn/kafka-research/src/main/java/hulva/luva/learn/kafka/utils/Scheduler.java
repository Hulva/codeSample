package hulva.luva.learn.kafka.utils;

import java.util.concurrent.TimeUnit;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年4月8日
 * @description
 *
 */
public interface Scheduler {
  void startup();

  void shutdown();

  boolean isStarted();

  void schedule(String name, Runnable func, Long delay, Long period, TimeUnit unit);
}
