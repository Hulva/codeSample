/**
 * @项目名称: elasticsearch 
 * @文件名称: MultiGet.java 
 * @Date: 2016年11月21日 
 * @Copyright: 2016 Hulva Luva.H All rights reserved. 
 * 
 */
package hulva.luva.codesample.elasticsearch.documentapi;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.Client;

/**
 * @author Hulva Luva.H
 *
 */
public class MultiGet {

	public void multiGet(Client client){
		MultiGetResponse multiGetItemResponses = client.prepareMultiGet()
			    .add("twitter", "tweet", "1")           
			    .add("twitter", "tweet", "2", "3", "4") 
			    .add("another", "type", "foo") 
			    .get();

			for (MultiGetItemResponse itemResponse : multiGetItemResponses) { 
			    GetResponse response = itemResponse.getResponse();
			    if (response.isExists()) {                      
			        String json = response.getSourceAsString(); 
			    }
			}
	}
}
