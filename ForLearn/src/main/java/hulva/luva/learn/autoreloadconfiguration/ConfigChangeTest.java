/**
 * 
 */
package hulva.luva.learn.autoreloadconfiguration;


/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年12月9日
 *
 */
public class ConfigChangeTest {
	private static final String FILE_PATH = "D:/workspace/ForLearn/src/main/java/configuration_test.properties";

	public static void main(String[] args) {
		AppConfiguration watchedConfig = new AppConfiguration(FILE_PATH);
		new Thread(watchedConfig).start();
		while (true) {
			try {
				Thread.sleep(2000);
				System.out.println(watchedConfig.getConfiguration("testKey"));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
