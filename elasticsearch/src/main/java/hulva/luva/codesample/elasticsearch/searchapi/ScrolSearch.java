/**
 * @项目名称: elasticsearch 
 * @文件名称: ScrolSearch.java 
 * @Date: 2016年11月21日 
 * @Copyright: 2016 Hulva Luva.H All rights reserved. 
 * 
 */
package hulva.luva.codesample.elasticsearch.searchapi;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.sort.SortParseElement;

/**
 * @author Hulva Luva.H
 *
 */
public class ScrolSearch {
	public SearchResponse doSearch(Client client){
		QueryBuilder qb = QueryBuilders.termQuery("multi", "test");
		SearchResponse scrollResp = client.prepareSearch("test")
		        .addSort(SortParseElement.DOC_FIELD_NAME, SortOrder.ASC)
		        .setScroll(new TimeValue(60000))
		        .setQuery(qb)
		        .setSize(100).execute().actionGet(); //100 hits per shard will be returned for each scroll
		//Scroll until no hits are returned
		while (true) {

		    for (SearchHit hit : scrollResp.getHits().getHits()) {
		        //Handle the hit...
		    }
		    scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
		    //Break condition: No hits are returned
		    if (scrollResp.getHits().getHits().length == 0) {
		        break;
		    }
		}
		return scrollResp;
	}

}
