package hulva.luva.learn.kafka.config;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年4月19日
 * @description
 *
 */
public interface Config {

    // Kafka config


    // Zookeeper path
    String AdminPath = "/admin";
    String BrokersPath = "/brokers";
    String ClusterPath = "/cluster";
    String ConfigPath = "/config";
    String ControllerPath = "/controller";
    String ControllerEpochPath = "/controller_epoch";
    String IsrChangeNotificationPath = "/isr_change_notification";
    String KafkaAclPath = "/kafka-acl";
    String KafkaAclChangesPath = "/kafka-acl-changes";

    String ConsumersPath = "/consumers";
    String ClusterIdPath = ClusterPath + "/id";
    String BrokerIdsPath = BrokersPath + "/ids";
    String BrokerTopicsPath = BrokersPath + "/topics";
    String ReassignPartitionsPath = AdminPath + "/reassign_partitions";
    String DeleteTopicsPath = AdminPath + "/delete_topics";
    String PreferredReplicaLeaderElectionPath = AdminPath + "/preferred_replica_election";
    String BrokerSequenceIdPath = BrokersPath + "/seqid";
    String ConfigChangesPath = ConfigPath + "/changes";
    String ConfigUsersPath = ConfigPath + "/users";
    String PidBlockPath = "/latest_pid_block";

}
