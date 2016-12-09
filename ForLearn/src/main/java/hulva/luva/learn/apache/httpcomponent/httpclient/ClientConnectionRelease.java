/**
 * 
 */
package hulva.luva.learn.apache.httpcomponent.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年11月23日
 *
 */
public class ClientConnectionRelease {

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet("http://10.16.238.82:9200/");

			System.out.println("Executing request " + httpget.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());

				// Get hold of the response entity
				HttpEntity entity = response.getEntity();
				System.out.println("----------------------------------------");
				// If the response does not enclose an entity, there is no need
				// to bother about connection release
				if (entity != null) {
					InputStream instream = entity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(entity.getContent(), "UTF-8"));
					try {
						String line = null;
						while ((line = reader.readLine()) != null) {
							System.out.println(line);
						}
					} catch (IOException ex) {
						// In case of an IOException the connection will be
						// released
						// back to the connection manager automatically
						throw ex;
					} finally {
						// Closing the input stream will trigger connection
						// release
						reader.close();
						instream.close();
					}
				}
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}

}
