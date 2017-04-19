package hulva.luva.learn.kafka.utils.zookeeper;

import java.util.ArrayList;
import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;

import hulva.luva.learn.kafka.log.Logging;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年4月8日
 * @description
 *
 */
public class ZkUtils extends Logging implements Watcher {
  private ZkClient zkClient;
  private ZkConnection zkConnection;
  private Boolean isSecure;

  private final String ADMINPATH = "/admin";
  private final String BROKERSPATH = "/brokers";
  private final String CLUSTERPATH = "/cluster";
  private final String CONFIGPATH = "/config";
  private final String CONTROLLERPATH = "/controller";
  private final String CONTROLLEREPOCHPATH = "/controller_epoch";
  private final String ISRCHANGENOTIFICATIONPATH = "/isr_change_notification";
  private final String KAFKAACLPATH = "/kafka-acl";
  private final String KAFKAACLCHANGESPATH = "/kafka-acl-changes";

  private final List<String> secureZkRootPaths = new ArrayList<String>();
  private final List<String> persistentZkPaths = new ArrayList<String>();
  private List<ACL> defaultAcls;

  public ZkUtils(ZkClient _zkClient, ZkConnection _zkConnection, boolean isSecure) {
    persistentZkPaths.add("/admin");
    persistentZkPaths.add("/brokers/ids");
    persistentZkPaths.add("/brokers/topics");
    persistentZkPaths.add("/config/changes");
    persistentZkPaths.add("/config/topics");
    persistentZkPaths.add("/config/clients");
    persistentZkPaths.add("/admin/delete_topics");
    persistentZkPaths.add("/brokers/seqid");
    persistentZkPaths.add("/isr_change_notification");
    persistentZkPaths.add("/latest_pid_block");

    defaultAcls = defaultAcls(isSecure);
    
    

  }
  
  

  public List<ACL> defaultAcls(boolean _isSecure) {
    if (_isSecure) {
      List<ACL> list = new ArrayList<ACL>();
      list.addAll(ZooDefs.Ids.CREATOR_ALL_ACL);
      list.addAll(ZooDefs.Ids.READ_ACL_UNSAFE);
      return list;
    } else {
      return ZooDefs.Ids.OPEN_ACL_UNSAFE;
    }

  }

  @Override
  public void process(WatchedEvent event) {
    // TODO Auto-generated method stub

  }


}
