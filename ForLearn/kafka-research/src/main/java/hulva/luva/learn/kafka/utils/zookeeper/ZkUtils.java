package hulva.luva.learn.kafka.utils.zookeeper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;

import hulva.luva.learn.kafka.config.Config;
import hulva.luva.learn.kafka.log.Logging;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年4月8日
 * @description
 *
 */
public class ZkUtils extends Logging implements Watcher, Config {

    public class ZkStringSerializer implements ZkSerializer {

        /*
         * (non-Javadoc)
         * 
         * @see org.I0Itec.zkclient.serialize.ZkSerializer#deserialize(byte[])
         */
        @Override
        public Object deserialize(byte[] bytes) throws ZkMarshallingError {
            Object object = null;
            try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                    ObjectInput in = new ObjectInputStream(bis);) {
                object = in.readObject();
            } catch (Exception e) {
                error("ZkStringSerializer deserialize error: ", e);
            }
            return object;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.I0Itec.zkclient.serialize.ZkSerializer#serialize(java.lang.Object)
         */
        @Override
        public byte[] serialize(Object data) throws ZkMarshallingError {
            byte[] bytes = null;
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutput out = new ObjectOutputStream(bos)) {
                out.writeObject(data);
                out.flush();
                bytes = bos.toByteArray();
            } catch (IOException e) {
                error("ZkStringSerializer serialize error: ", e);
            }
            return bytes;
        }

    }

    private ZkClient zkClient;
    private ZkConnection zkConnection;
    private Boolean isSecure;

    private final List<String> secureZkRootPaths = new ArrayList<String>();
    private final List<String> persistentZkPaths = new ArrayList<String>();
    private final List<String> sensitiveZkRootPaths = new ArrayList<String>();
    private List<ACL> defaultAcls;

    public ZkUtils(String zkUrl, int sessionTimeout, int connectionTimeout,
            boolean isZkSecurityEnabled) {
        zkConnection = new ZkConnection(zkUrl, sessionTimeout);
        zkClient = new ZkClient(zkConnection, connectionTimeout, new ZkStringSerializer());
        new ZkUtils(zkClient, zkConnection, isZkSecurityEnabled);
    }

    public ZkUtils(ZkClient _zkClient, ZkConnection _zkConnection, boolean isSecure) {
        persistentZkPaths.add(AdminPath);
        persistentZkPaths.add(BrokersPath);
        persistentZkPaths.add(BrokerTopicsPath);
        persistentZkPaths.add(ConfigChangesPath);
        persistentZkPaths.add(ConfigPath + "/topics");
        persistentZkPaths.add(ConfigPath + "/clients");
        persistentZkPaths.add(DeleteTopicsPath);
        persistentZkPaths.add(BrokerSequenceIdPath);
        persistentZkPaths.add(IsrChangeNotificationPath);
        persistentZkPaths.add(PidBlockPath);

        sensitiveZkRootPaths.add(ConfigUsersPath);

        defaultAcls = defaultAcls(isSecure, "");



    }



    public List<ACL> defaultAcls(boolean _isSecure, String path) {
        if (_isSecure) {
            List<ACL> list = new ArrayList<ACL>();
            list.addAll(ZooDefs.Ids.CREATOR_ALL_ACL);
            if (!sensitivePath(path)) {
                list.addAll(ZooDefs.Ids.READ_ACL_UNSAFE);
            }
            return list;
        } else {
            return ZooDefs.Ids.OPEN_ACL_UNSAFE;
        }

    }

    public boolean sensitivePath(String path) {
        return path != null && sensitiveZkRootPaths.contains(path);
    }

    void maybeDeletePath(String zkUrl, String dir) {
        ZkClient zk = createZkClient(zkUrl, 30 * 1000, 30 * 1000);
        zk.deleteRecursive(dir);
        zk.close();
    }

    private ZkClient createZkClient(String zkUrl, int sessionTimeout, int connectionTimeout) {
        return new ZkClient(zkUrl, sessionTimeout, connectionTimeout);
    }

    public String getTopicPath(String topic) {
        return BrokerTopicsPath + "/" + topic;
    }

    public String getTopicPartitionsPath(String topic) {
        return getTopicPath(topic) + "/partitions";
    }

    public String getTopicPartitionPath(String topic, int partitionId) {
        return getTopicPartitionsPath(topic) + "/" + partitionId;
    }

    public String getTopicPartitionLeaderAndIsrPath(String topic, int partitionId) {
        return getTopicPartitionPath(topic, partitionId) + "/state";
    }

    @Override
    public void process(WatchedEvent event) {
        // TODO Auto-generated method stub

    }


}
