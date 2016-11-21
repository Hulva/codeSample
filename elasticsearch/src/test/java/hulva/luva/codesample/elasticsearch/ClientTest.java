/**
 * @项目名称: elasticsearch 
 * @文件名称: ClientTest.java 
 * @Date: 2016年11月21日 
 * @Copyright: 2016 Hulva Luva.H All rights reserved. 
 * 
 */
package hulva.luva.codesample.elasticsearch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.client.Client;

import hulva.luva.codesample.elasticsearch.util.ClientFactory;
import hulva.luva.codesample.elasticsearch.util.ConfigureLoader;

/**
 * @author Hulva Luva.H
 *
 */
public class ClientTest {
	private static Log logger = LogFactory.getLog(ClientTest.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ConfigureLoader();
		Client client = ClientFactory.getTransportClient("hulvia", false);
		logger.info(client.settings().getAsMap());
	}

}
