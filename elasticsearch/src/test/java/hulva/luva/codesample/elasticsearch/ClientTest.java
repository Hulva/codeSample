/**
 * @项目名称: elasticsearch 
 * @文件名称: ClientTest.java 
 * @Date: 2016年11月21日 
 * @Copyright: 2016 Hulva Luva.H All rights reserved. 
 * 
 */
package hulva.luva.codesample.elasticsearch;

import hulva.luva.codesample.elasticsearch.util.ClientFactory;
import hulva.luva.codesample.elasticsearch.util.ConfigureLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;

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
		Client client = ClientFactory.getTransportClient("test-es", false);
		SearchResponse response = client.prepareSearch("ec_shoppingcart_2016-11-19")
				.setTypes("ec_shoppingcart")
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.termQuery("HttpMethod", "POST")) // Query
				.setFrom(0).setSize(60).setExplain(true)
				// .setTerminateAfter(docCount)
				.execute().actionGet();
		logger.info(response);
		/*
		 * ElastiSearchService ess = ElastiSearchService.getInstance();
		 * XContentBuilder jsonData = jsonBuilder() ess.createIndex("luva_test",
		 * "test", "test", jsonData);
		 */
	}

}
