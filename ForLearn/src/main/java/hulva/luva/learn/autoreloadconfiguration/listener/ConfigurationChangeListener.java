/**
 * 
 */
package hulva.luva.learn.autoreloadconfiguration.listener;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年12月9日
 *
 */

public abstract class ConfigurationChangeListener implements Runnable {

	private static Log LOG = LogFactory
			.getLog(ConfigurationChangeListener.class);

	private String configurationFileName = null;
	private String fullFilePath = null;

	public ConfigurationChangeListener(final String filePath) {
		super();
		this.fullFilePath = filePath;
	}

	@Override
	public void run() {
		try {
			register(this.fullFilePath);
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("regiter failed: " + e.getMessage());
		}
	}

	private void register(final String file) throws IOException {
		final int lastIndex = file.lastIndexOf("/");
		String dirPath = file.substring(0, lastIndex + 1);
		String fileName = file.substring(lastIndex + 1, file.length());
		this.configurationFileName = fileName;

		configurationChanged(file);
		startWatcher(dirPath, fileName);
	}

	private void startWatcher(String dirPath, String fileName)
			throws IOException {
		final WatchService watchService = FileSystems.getDefault()
				.newWatchService();
		Path path = Paths.get(dirPath);
		path.register(watchService, ENTRY_MODIFY);

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					watchService.close();
				} catch (IOException e) {
					e.printStackTrace();
					LOG.error("WatchService close failed: " + e.getMessage());
				}
			}
		});

		WatchKey key = null;
		while (true) {
			try {
				/*
				 * WatchService provides two methods take() and poll(). While
				 * take() method wait for next change to happen and until it is
				 * blocked, poll() immediately check for change event. If
				 * nothing changed from last poll() call, it will return null.
				 * poll() method does not block the execution, so should be
				 * called in a Thread with some sleep time.
				 */
				key = watchService.take();
				for (WatchEvent<?> event : key.pollEvents()) {
					if (event.context().toString()
							.equals(configurationFileName)) {
						configurationChanged(dirPath + fileName);
					}
				}
				boolean reset = key.reset();
				if (!reset) {
					LOG.info("Could not reset the watch key.");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				LOG.error("InterruptedException: " + e.getMessage());
			}
		}
	}

	protected abstract void configurationChanged(final String file);

}
