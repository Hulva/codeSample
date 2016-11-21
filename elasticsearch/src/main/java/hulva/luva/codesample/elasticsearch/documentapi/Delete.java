/**
 * @项目名称: elasticsearch 
 * @文件名称: Delete.java 
 * @Date: 2016年11月21日 
 * @Copyright: 2016 Hulva Luva.H All rights reserved. 
 * 
 */
package hulva.luva.codesample.elasticsearch.documentapi;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.Client;

import hulva.luva.codesample.elasticsearch.Document;

/**
 * @author Hulva Luva.H
 *
 */
public class Delete {
	DeleteResponse response = null;

	public void indexDocument(Client client, Document document, boolean operationThreaded) {
		response = client.prepareDelete(document.getIndex(), document.getType(), document.getId())
				.get();
	}
}
