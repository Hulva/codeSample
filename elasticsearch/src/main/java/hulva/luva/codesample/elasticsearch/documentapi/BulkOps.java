/**
 * 
 */
package hulva.luva.codesample.elasticsearch.documentapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import hulva.luva.codesample.elasticsearch.Document;

/**
 * @author lvswe
 *
 */
public class BulkOps {

	private static Log logger = LogFactory.getLog(BulkOps.class);

	private static ObjectMapper mapper = new ObjectMapper();

	public static void push(BufferedReader in, Client client, Document document) {

		/*
		 * 添加当前时间为@timestamp
		 * 
		 * Calendar calendar = Calendar.getInstance();
		 * 
		 * calendar.add(Calendar.DAY_OF_MONTH, -1);
		 * 
		 * SimpleDateFormat keyFormat = new SimpleDateFormat("yyyyMMdd");
		 * 
		 * String keyDate = keyFormat.format(calendar.getTime());
		 */

		String line;
		try {
			BulkRequestBuilder bulkRequest = client.prepareBulk();

			while ((line = in.readLine()) != null) {
				try {
					Map<String, Object> logMap = mapper.readValue(line, new TypeReference<Map<String, Object>>() {
					});

					long timestamp = MapUtils.getLong(logMap, "timestamp");

					Map<String, Object> logCollectMap = new HashMap<String, Object>();
					logCollectMap.put("PAYLOAD", line);

					logCollectMap.put("@TIMESTAMP", timestamp);

					bulkRequest.add(client.prepareIndex(document.getIndex(), document.getType(), document.getId())
							.setSource(XContentFactory.jsonBuilder().startObject().field("PAYLOAD", logMap)
									.field("@TIMESTAMP", timestamp).endObject()));
				} catch (IOException e) {
					logger.error(e.getMessage());
					e.printStackTrace();
				}
			}

			BulkResponse bulkResponse = bulkRequest.execute().actionGet();

			if (bulkResponse.hasFailures()) {
				logger.error(bulkResponse.buildFailureMessage());
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		client.close();
	}

	public static void pull(Client client) {

	}

}
