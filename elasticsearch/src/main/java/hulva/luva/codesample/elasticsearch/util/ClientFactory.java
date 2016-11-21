/**
 * @项目名称: elasticsearch 
 * @文件名称: ClientFactory.java 
 * @Date: 2016年11月21日 
 * @Copyright: 2016 Hulva Luva.H All rights reserved. 
 * 
 */
package hulva.luva.codesample.elasticsearch.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;

/**
 * @author Hulva Luva.H
 *
 */
public class ClientFactory {
	private static Log logger = LogFactory.getLog(ClientFactory.class);

	private static Client client;

	public static Client getTransportClient(String clusterName, boolean sniff) {
		Settings settings = null;
		if (client == null) {
			if (sniff) {
				settings = Settings.settingsBuilder().put("client.transport.sniff", sniff).put("cluster.name", clusterName)
						.build();
				/**
				 * 					Parameter			   | Description
				 * 
				 * client.transport.ignore_cluster_name    | Set to true to ignore cluster name validation of connected nodes. (since 0.19.4)
				 * 
				 * client.transport.ping_timeout		   | The time to wait for a ping response from a node. Defaults to 5s.
				 *
				 * client.transport.nodes_sampler_interval | How often to sample / ping the nodes listed and connected. Defaults to 5s.
				 */

			} else {
				settings = Settings.settingsBuilder().put("cluster.name", clusterName).build();
			}
			try {
				TransportAddress[] hostsList = new TransportAddress[ConfigureLoader.hosts.size()];
				int i = 0;
				for (String host : ConfigureLoader.hosts.keySet()) {
					hostsList[i] = new InetSocketTransportAddress(InetAddress.getByName(host),
							ConfigureLoader.hosts.get(host));
					i++;
				}
				client = TransportClient.builder().settings(settings).build().addTransportAddresses(hostsList);
			} catch (UnknownHostException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return client;
	}


	public static void close() {
		client.close();
	}

	/*public static Client getNodeClient(String clusterName) {
		Settings settings = Settings.settingsBuilder().put("client.transport.sniff", true)
				.put("cluster.name", clusterName).build();
		Node server = NodeBuilder.nodeBuilder().clusterName(clusterName).client(true).settings(settings).node().start();

		return server.client();
	}*/
}
