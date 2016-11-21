/**
 * @项目名称: elasticsearch 
 * @文件名称: Index.java 
 * @Date: 2016年11月21日 
 * @Copyright: 2016 Hulva Luva.H All rights reserved. 
 * 
 */
package hulva.luva.codesample.elasticsearch.documentapi;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;

import hulva.luva.codesample.elasticsearch.Document;

/**
 * @author Hulva Luva.H
 *
 */
public class Index {

	IndexResponse response = null;

	public void indexDocument(String json, Client client, Document document) {
		response = client.prepareIndex(document.getIndex(), document.getType(), document.getId()).setSource(json).get();
	}
	
	
	/**
	 * XContentBuilder builder = jsonBuilder()
    .startObject()
        .field("user", "kimchy")
        .field("postDate", new Date())
        .field("message", "trying out Elasticsearch")
    .endObject()
	 * @param json
	 * @param client
	 * @param document
	 */
	public void indexDocument(XContentBuilder json, Client client, Document document) {
		response = client.prepareIndex(document.getIndex(), document.getType(), document.getId()).setSource(json).get();
	}
}
