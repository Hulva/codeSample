package hulva.luva.codesample.elasticsearch;

import java.util.List;
import org.elasticsearch.action.search.MultiSearchRequestBuilder;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Hulva Luva.H
 *
 */
public class ElasticSearchQueries {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchQueries.class);

    public SearchHits findInIndex(String index, String fieldName, String fieldValue) {
        try {
            SearchResponse response = ElasticSearchUtil.getClient().prepareSearch(index)
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setQuery(QueryBuilders.termQuery(fieldName, fieldValue)) // Query
                    //.setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18)) // Filter
                    .setFrom(0).setSize(60).setExplain(true)
                    .execute()
                    .actionGet();
            return response.getHits();
        } catch (Exception e) {
            logger.error("findInIndex", e);
        }
        return null;
    }

    public SearchHits findInCluster(String key, String value) {
        try {
            SearchResponse response = ElasticSearchUtil.getClient().prepareSearch()
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setQuery(QueryBuilders.termQuery(key, value)) // Query
                    .execute()
                    .actionGet();
            return response.getHits();
        } catch (Exception e) {
            logger.error("findInCluster", e);
        }
        return null;
    }

    public SearchHits findByQuery(QueryBuilder builder) {
        try {
            SearchResponse response = ElasticSearchUtil.getClient().prepareSearch()
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setQuery(builder) // Query
                    .execute()
                    .actionGet();
            return response.getHits();
        } catch (Exception exception) {
            logger.error(" findByQuery ", exception);
        }
        return null;
    }

    public MultiSearchResponse multiSearch(List<SearchRequestBuilder> searchRequestList) {
        try {
            MultiSearchRequestBuilder builder = ElasticSearchUtil.getClient().prepareMultiSearch();
            for (SearchRequestBuilder requestBuilder : searchRequestList) {
                builder.add(requestBuilder);
            }
            return builder.execute().actionGet();
        } catch (Exception e) {

        }
        return null;
    }
    
}
