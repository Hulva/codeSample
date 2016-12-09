/**
 * 
 */
package hulva.luva.codesample.elasticsearch.util;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.elasticsearch.cluster.health.ClusterIndexHealth;

/**
 * @ClassName
 * @Description
 * @author lh10
 * @date 2016年11月22日
 *
 */
public class AdministrationApi {

	public void checkHealth(Client client) {
		ClusterHealthResponse healths = client.admin().cluster()
				.prepareHealth().get();
		String clusterName = healths.getClusterName();
		int numberOfDataNodes = healths.getNumberOfDataNodes();
		int numberOfNodes = healths.getNumberOfNodes();

		/*for (ClusterIndexHealth health : healths) {
			String index = health.getIndex();
			int numberOfShards = health.getNumberOfShards();
			int numberOfReplicas = health.getNumberOfReplicas();
			ClusterHealthStatus status = health.getStatus();
		}*/
	}

}
