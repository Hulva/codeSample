/**
 * 
 */
package hulva.luva.learn.autoreloadconfiguration;

import hulva.luva.learn.autoreloadconfiguration.listener.ConfigurationChangeListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年12月9日
 *
 */
public class AppConfiguration extends ConfigurationChangeListener {
	public AppConfiguration(String filePath) {
		super(filePath);
	}

	private static Properties configuration = new Properties();

	private static Properties getConfiguration() {
		return configuration;
	}

	public void initialize(final String file) {
		InputStream in = null;
		try {
			in = new FileInputStream(new File(file));
			configuration.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getConfiguration(final String key) {
		return getConfiguration().getProperty(key);
	}

	public String getConfigurationWithDefaultValue(final String key,
			final String defaultValue) {
		return getConfiguration().getProperty(key, defaultValue);

	}

	@Override
	protected void configurationChanged(String file) {
		initialize(file);
	}
}
