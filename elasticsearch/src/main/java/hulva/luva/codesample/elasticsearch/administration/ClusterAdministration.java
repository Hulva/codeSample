/**
 * 
 */
package hulva.luva.codesample.elasticsearch.administration;

import java.util.Map;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.elasticsearch.cluster.health.ClusterIndexHealth;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年11月26日
 *
 */
public class ClusterAdministration {
	private ClusterAdminClient clusterAdminClient;

	public ClusterAdministration(Client client) {
		super();
		this.clusterAdminClient = client.admin().cluster();
	}
	
	public void printHealInfo(){
		StringBuilder sb = new StringBuilder();
		ClusterHealthResponse healths = clusterAdminClient.prepareHealth().get(); 
		sb.append("ClusterName=").append(healths.getClusterName())
		.append("\n")
		.append("NumberOfDataNodes=").append(healths.getNumberOfDataNodes())
		.append("\n")
		.append("NumberOfNodes=").append(healths.getNumberOfNodes());

		Map<String, ClusterIndexHealth> clusterIndexHealths = healths.getIndices();
		
		ClusterIndexHealth indexHealth = null;
		for (String indexName : clusterIndexHealths.keySet()) {
			indexHealth = clusterIndexHealths.get(indexName);
		    String index = indexHealth.getIndex();                       
		    int numberOfShards = indexHealth.getNumberOfShards();        
		    int numberOfReplicas = indexHealth.getNumberOfReplicas();    
		    ClusterHealthStatus status = indexHealth.getStatus();        
		}
	}
	
	
}
