/**
 * @项目名称: elasticsearch 
 * @文件名称: BulkProcessor.java 
 * @Date: 2016年11月21日 
 * @Copyright: 2016 Hulva Luva.H All rights reserved. 
 * 
 */
package hulva.luva.codesample.elasticsearch.documentapi;

import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;

/**
 * @author Hulva Luva.H
 *
 */
public class BulkProcessorOps {
	
	BulkProcessor bulkProcessor = null;

	public BulkProcessorOps(Client client) {
		bulkProcessor = BulkProcessor.builder(client, new BulkProcessor.Listener() {

			@Override
			public void beforeBulk(long executionId, BulkRequest request) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
				// TODO Auto-generated method stub

			}
		}).setBulkActions(10000).setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB))
				.setFlushInterval(TimeValue.timeValueSeconds(5)).setConcurrentRequests(1)
				.setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3)).build();
	}
	
	public void start(){
		bulkProcessor.add((IndexRequest) new IndexRequest("twitter", "tweet", "1").source(/* your doc here */));
		bulkProcessor.add(new DeleteRequest("twitter", "tweet", "2"));
		try {
			bulkProcessor.awaitClose(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		bulkProcessor.close();
	}

}
