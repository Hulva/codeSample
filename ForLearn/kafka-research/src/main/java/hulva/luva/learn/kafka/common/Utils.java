package hulva.luva.learn.kafka.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年4月7日
 * @description
 *
 */
public class Utils {
  private static final Logger log = LoggerFactory.getLogger(Utils.class);

  /**
   * Read a properties file from the given path
   * 
   * @param filename The path of the file to read
   */
  public static Properties loadProps(String filename) throws IOException, FileNotFoundException {
    Properties props = new Properties();
    try (InputStream propStream = new FileInputStream(filename)) {
      props.load(propStream);
    }
    return props;
  }

  public static Thread newThread(String name, Runnable runnable, boolean daemon) {
    Thread thread = new Thread(runnable, name);
    thread.setDaemon(daemon);
    thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
      public void uncaughtException(Thread t, Throwable e) {
        log.error("Uncaught exception in thread '{}':", t.getName(), e);
      }
    });
    return thread;
  }
}
