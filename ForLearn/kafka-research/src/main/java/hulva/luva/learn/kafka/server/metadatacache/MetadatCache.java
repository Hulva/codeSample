package hulva.luva.learn.kafka.server.metadatacache;

import hulva.luva.learn.kafka.log.Logging;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年4月20日
 * @description
 *
 */
public class MetadatCache extends Logging {


    public MetadatCache(int brokerId) {
        this.logIdent = "[Kafka Metadata Cache on broker " + brokerId + "]";

    }
    
    
}
