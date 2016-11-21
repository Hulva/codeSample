/**
 * @项目名称: elasticsearch 
 * @文件名称: ConfigureLoader.java 
 * @Date: 2016年11月21日 
 * @Copyright: 2016 Hulva Luva.H All rights reserved. 
 * 
 */
package hulva.luva.codesample.elasticsearch.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Hulva Luva.H
 *
 */
public class ConfigureLoader {
	private static Log logger = LogFactory.getLog(ConfigureLoader.class);

	static Map<String, Integer> hosts = new HashMap<String, Integer>();
	
	

	public ConfigureLoader() {
		super();
		init();
	}



	private static void init() {
		Properties prop = new Properties();
		try {
			prop.load(ConfigureLoader.class.getClassLoader().getResourceAsStream("conf.properties"));
		} catch (FileNotFoundException ffe) {
			logger.error(ffe.getMessage());
			ffe.printStackTrace();
		} catch (IOException ioe) {
			logger.error(ioe.getMessage());
			ioe.printStackTrace();
		}

		String hostsPairs = prop.getProperty("elasticsearch.cluster.hosts");
		String[] hostsPairArray = hostsPairs.split(",");
		for (String hostPair : hostsPairArray) {
			String[] host_port = hostPair.split(":");
			hosts.put(host_port[0], Integer.parseInt(host_port[1]));
		}
	}
}
