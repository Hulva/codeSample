/**
 * @项目名称: elasticsearch 
 * @文件名称: BaseQuery.java 
 * @Date: 2016年11月21日 
 * @Copyright: 2016 Hulva Luva.H All rights reserved. 
 * 
 */
package hulva.luva.codesample.elasticsearch.searchapi;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * @author Hulva Luva.H
 *
 */
public class BaseQuery {

	public SearchResponse doSearch(Client client/*, int docCount*/){
		SearchResponse response = client.prepareSearch("index1", "index2")
		        .setTypes("type1", "type2")
		        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		        .setQuery(QueryBuilders.termQuery("multi", "test"))                 // Query
		        .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
		        .setFrom(0).setSize(60).setExplain(true)
		        //.setTerminateAfter(docCount)
		        .execute()
		        .actionGet();
		return response;
		
		// MatchAll on the whole cluster with all default options
//		SearchResponse response = client.prepareSearch().execute().actionGet();
	}
}
