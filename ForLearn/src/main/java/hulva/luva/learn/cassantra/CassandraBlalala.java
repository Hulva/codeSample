/**
 * 
 */
package hulva.luva.learn.cassantra;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.NoHostAvailableException;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年11月23日
 *
 */
public class CassandraBlalala {
	static Log LOG = LogFactory.getLog(CassandraBlalala.class);

	private static final String KEYSPACE = "\"APIProfile\"";

	public static void main(String[] args) {
		Cluster cluster = null;
		Session session;

		try {
			PoolingOptions poolingOptions = new PoolingOptions();
			poolingOptions.setConnectionsPerHost(HostDistance.REMOTE, 2, 4);

			cluster = Cluster.builder().addContactPoints("172.16.13.81")
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
			System.out.println("Cassandra Cluster: " + s.toString());

			// session = cluster.connectAsync();
			session = cluster.connect(KEYSPACE);
			// ResultSetFuture rsf = session
			// .executeAsync("SELECT * FROM \"APIProfile\".\"ProfileLog\" LIMIT 10");
			String profileKeys = "";
			for(int i=0;i<9999;i++){
				String profileKey="WH7|15d5"+(Math.random()*10)+"d12-6ba4-4"+(Math.random()*10)+"e3-8b"+(Math.random()*10)+"8-2cd03a"+(Math.random()*10)+"a828a|8508f830-522e-a10f-fdc3-32c1b1835ecb";
				profileKeys+="'"+profileKey+"',";
			}
			ResultSetFuture rsf = session
					.executeAsync("SELECT * FROM \"APIProfile\".\"ProfileLog\" WHERE key IN ("+profileKeys.substring(0, profileKeys.length()-1)+")");
			ResultSet rs = rsf.get();
			List<Row> rowList = rs.all();
			for (Row row : rowList) {
				LOG.info(row.getObject(0));
				LOG.info(row.getObject(2));
				LOG.info(row);
			}
			LOG.info(session.getLoggedKeyspace());
		} catch (NoHostAvailableException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		} finally {
			if (cluster != null) {
				cluster.close();
			}
		}
	}
}
