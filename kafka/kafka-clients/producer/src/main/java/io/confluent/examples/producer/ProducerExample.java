/**
 * Copyright 2015 Confluent Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.confluent.examples.producer;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONObject;

public class ProducerExample {
    public static void main(String[] args) {
        // if (args.length ==0) {
        // System.out.println("Please provide command line arguments: numEvents schemaRegistryUrl");
        // System.exit(-1);
        // }
        // long events = Long.parseLong(args[0]);

        Properties props = new Properties();
        // props.put("bootstrap.servers", "lc7003.luva.h:6667,c7001.luva.h:6667,c7002.luva.h:6667");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // props.put("schema.registry.url", url);

        Producer<String, String> producer = new KafkaProducer<String, String>(props);

        Random rnd = new Random();
        while (true) {
            // for (long nEvents = 0; nEvents < events; nEvents++) {
            long runtime = new Date().getTime();
            String site = "www.example.com";
            String ip = "192.168.2." + rnd.nextInt(255);
            JSONObject page_visit = new JSONObject();
            page_visit.put("time", runtime);
            page_visit.put("site", site);
            page_visit.put("ip", ip);

            ProducerRecord<String, String> data =
                    new ProducerRecord<String, String>(args[0], ip, page_visit.toString());
            producer.send(data);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // producer.close();
    }
}
