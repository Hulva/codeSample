package hulva.luva.learn.kafka.server;

import hulva.luva.learn.kafka.log.Logging;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年4月8日
 * @description 
 *
 */
public class KafkaServer extends Logging {
  private KafkaServer kafkaScheduler;

  public void startup(){
    kafkaScheduler.startup();
  }
  
}
