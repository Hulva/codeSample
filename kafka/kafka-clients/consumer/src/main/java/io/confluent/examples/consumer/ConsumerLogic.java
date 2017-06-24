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
package io.confluent.examples.consumer;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

public class ConsumerLogic implements Runnable {
    private KafkaStream<byte[], byte[]> stream;
    private int threadNumber;

    public ConsumerLogic(KafkaStream<byte[], byte[]> stream, int threadNumber) {
        this.threadNumber = threadNumber;
        this.stream = stream;
    }

    public void run() {
        ConsumerIterator<byte[], byte[]> it = stream.iterator();

        while (true) {
            MessageAndMetadata<byte[], byte[]> record = it.next();

            String topic = record.topic();
            int partition = record.partition();
            long offset = record.offset();
            Object key = record.key();
            System.out.println("Thread " + threadNumber + " received: " + "Topic " + topic
                    + " Partition " + partition + " Offset " + offset + " Key " + key + " Message "
                    + new String(record.message()));

            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
