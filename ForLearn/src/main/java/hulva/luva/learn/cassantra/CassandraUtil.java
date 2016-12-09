/**
 * 
 */
package hulva.luva.learn.cassantra;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.NoHostAvailableException;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年11月25日
 *
 */
public class CassandraUtil {
	static Log LOG = LogFactory.getLog(CassandraUtil.class);
	private static Cluster cluster = null;
	private static Session session;

	public static void connectToCluster(String[] addresses) {
		try {
			PoolingOptions poolingOptions = new PoolingOptions();
			poolingOptions.setConnectionsPerHost(HostDistance.REMOTE, 2, 4);

			cluster = Cluster.builder().addContactPoints(addresses)
					.withPoolingOptions(poolingOptions).build();

			StringBuilder s = new StringBuilder();
			Set<Host> allHosts = cluster.getMetadata().getAllHosts();
			for (Host h : allHosts) {
				s.append("[");
				s.append(h.getDatacenter());
				s.append("-");
				s.append(h.getRack());
				s.append("-");
				s.append(h.getAddress());
				s.append("]");
			}
			LOG.info("Cassandra Cluster: " + s.toString());
		} catch (NoHostAvailableException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public static Session getSession(String keyspace) {
		if (session == null) {
			session = cluster.connect(keyspace);
		}
		return session;
	}
}
