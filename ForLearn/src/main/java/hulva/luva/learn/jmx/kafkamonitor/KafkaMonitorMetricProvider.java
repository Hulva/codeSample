/**
 * 
 */
package hulva.luva.learn.jmx.kafkamonitor;

import java.io.IOException;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2017年1月4日
 *
 */
public class KafkaMonitorMetricProvider {

	protected final Logger LOGGER = LoggerFactory
			.getLogger(KafkaMonitorMetricProvider.class);
	private static final String MESSAGE_IN_PER_SEC = "kafka.server:type=BrokerTopicMetrics,name=MessagesInPerSec";
	private static final String BYTES_IN_PER_SEC = "kafka.server:type=BrokerTopicMetrics,name=BytesInPerSec";
	private static final String BYTES_OUT_PER_SEC = "kafka.server:type=BrokerTopicMetrics,name=BytesOutPerSec";
	private static final String PRODUCE_REQUEST_PER_SEC = "kafka.network:type=RequestMetrics,name=RequestsPerSec,request=Produce";
	private static final String CONSUMER_REQUEST_PER_SEC = "kafka.network:type=RequestMetrics,name=RequestsPerSec,request=FetchConsumer";
	private static final String FLOWER_REQUEST_PER_SEC = "kafka.network:type=RequestMetrics,name=RequestsPerSec,request=FetchFollower";
	private static final String ACTIVE_CONTROLLER_COUNT = "kafka.controller:type=KafkaController,name=ActiveControllerCount";
	private static final String PART_COUNT = "kafka.server:type=ReplicaManager,name=PartitionCount";

	public void extractMonitorData() {
		// TODO 通过调用API获得IP以及参数
		String jmxURL = "service:jmx:rmi:///jndi/rmi://10.16.238.91:55555/jmxrmi";
		try {
			MBeanServerConnection jmxConnection = getMBeanServerConnection(jmxURL);
			ObjectName messageCountObj = new ObjectName(MESSAGE_IN_PER_SEC);
			ObjectName bytesInPerSecObj = new ObjectName(BYTES_IN_PER_SEC);
			ObjectName bytesOutPerSecObj = new ObjectName(BYTES_OUT_PER_SEC);
			ObjectName produceRequestsPerSecObj = new ObjectName(
					PRODUCE_REQUEST_PER_SEC);
			ObjectName consumerRequestsPerSecObj = new ObjectName(
					CONSUMER_REQUEST_PER_SEC);
			ObjectName flowerRequestsPerSecObj = new ObjectName(
					FLOWER_REQUEST_PER_SEC);
			ObjectName activeControllerCountObj = new ObjectName(
					ACTIVE_CONTROLLER_COUNT);
			ObjectName partCountObj = new ObjectName(PART_COUNT);
			Long messagesInPerSec = (Long) jmxConnection.getAttribute(
					messageCountObj, "Count");
			Long bytesInPerSec = (Long) jmxConnection.getAttribute(
					bytesInPerSecObj, "Count");
			Long bytesOutPerSec = (Long) jmxConnection.getAttribute(
					bytesOutPerSecObj, "Count");
			Long produceRequestCountPerSec = (Long) jmxConnection.getAttribute(
					produceRequestsPerSecObj, "Count");
			Long consumerRequestCountPerSec = (Long) jmxConnection
					.getAttribute(consumerRequestsPerSecObj, "Count");
			Long flowerRequestCountPerSec = (Long) jmxConnection.getAttribute(
					flowerRequestsPerSecObj, "Count");
			Integer activeControllerCount = (Integer) jmxConnection
					.getAttribute(activeControllerCountObj, "Value");
			Integer partCount = (Integer) jmxConnection.getAttribute(
					partCountObj, "Value");
			LOGGER.info(messagesInPerSec + "");
			LOGGER.info(bytesInPerSec + "");
			LOGGER.info(bytesOutPerSec + "");
			LOGGER.info(produceRequestCountPerSec + "");
			LOGGER.info(consumerRequestCountPerSec + "");
			LOGGER.info(flowerRequestCountPerSec + "");
			LOGGER.info(activeControllerCount + "");
			LOGGER.info(partCount + "");
//			LOGGER.info(jmxConnection.getAttribute(new ));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		} catch (AttributeNotFoundException e) {
			e.printStackTrace();
		} catch (MBeanException e) {
			e.printStackTrace();
		} catch (ReflectionException e) {
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new KafkaMonitorMetricProvider().extractMonitorData();
	}

	/**
	 * 获得MBeanServer 的连接
	 * 
	 * @param jmxUrl
	 * @return
	 * @throws IOException
	 */
	public MBeanServerConnection getMBeanServerConnection(String jmxUrl)
			throws IOException {
		JMXServiceURL url = new JMXServiceURL(jmxUrl);
		JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
		MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
		return mbsc;
	}
}
