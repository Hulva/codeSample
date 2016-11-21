/**
 * @项目名称: elasticsearch 
 * @文件名称: MultiSearch.java 
 * @Date: 2016年11月21日 
 * @Copyright: 2016 Hulva Luva.H All rights reserved. 
 * 
 */
package hulva.luva.codesample.elasticsearch.searchapi;

import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * @author Hulva Luva.H
 *
 */
public class MultiSearch {

	public void doMultiSearch(Client client){
		SearchRequestBuilder srb1 = client
			    .prepareSearch().setQuery(QueryBuilders.queryStringQuery("elasticsearch")).setSize(1);
			SearchRequestBuilder srb2 = client
			    .prepareSearch().setQuery(QueryBuilders.matchQuery("name", "kimchy")).setSize(1);

			MultiSearchResponse sr = client.prepareMultiSearch()
			        .add(srb1)
			        .add(srb2)
			        .execute().actionGet();

			// You will get all individual responses from MultiSearchResponse#getResponses()
			long nbHits = 0;
			for (MultiSearchResponse.Item item : sr.getResponses()) {
			    SearchResponse response = item.getResponse();
			    nbHits += response.getHits().getTotalHits();
			}
	}
}
