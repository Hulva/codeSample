/**
 * 
 */
package hulva.luva.learn.cassantra;

import java.util.HashMap;
import java.util.Map;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年11月25日
 *
 */
public class StatementCache {

	Map<String, PreparedStatement> statementCache = new HashMap<>();

	public BoundStatement getStatement(Session session, String cql) {
		PreparedStatement ps = statementCache.get(cql);
		// no statement cached, create one or cache it now
		if (ps == null) {
			ps = session.prepare(cql);
			statementCache.put(cql, ps);
		}
		return ps.bind();
	}
}
