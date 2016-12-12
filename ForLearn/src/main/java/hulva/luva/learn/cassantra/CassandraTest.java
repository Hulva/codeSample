/**
 * 
 */
package hulva.luva.learn.cassantra;

import com.datastax.driver.core.Session;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年11月25日
 *
 */
public class CassandraTest {
	public static void main(String[] args) {
		String[] addresses = { "172.16.13.83", "172.16.13.82", "172.16.13.84",
				"172.16.13.85", "172.16.13.81", "172.16.13.80", "172.16.13.79" };
		CassandraUtil.connectToCluster(addresses);
		Session session = CassandraUtil.getSession("\"APIProfile\"");
		StatementCache sCache = new StatementCache();
		String profileKeys = "";

		// 'WH7|15d53d12-6ba4-45e3-8b58-2cd03a9a828a|cd674b37-2eb0-141f-ff95-64b53d7c2451','WH7|15d53d12-6ba4-45e3-8b58-2cd03a9a828a|8508f830-522e-a10f-fdc3-32c1b1835ecb'
		String cql = "SELECT * FROM \"APIProfile\".\"ProfileLog\" WHERE key IN ("
				+ profileKeys + ")";
		sCache.getStatement(session, "");
	}
}
