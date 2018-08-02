package com.talkingdata.birdie.test.flink.operator;

import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

import java.util.Properties;


/**
 * Created by jian.wang on 2017/5/20.
 */
public class KafkaSource extends FlinkKafkaConsumer09<String> implements Operatorable {
    public KafkaSource() {
        super("testTopic", new SimpleStringSchema(), new abcd());
    }

    public String getType() {
        return "source";
    }

    private static class abcd extends Properties {
        public abcd() {
            this.put("fetch.message.max.bytes", 10485760);
            this.put("socket.receive.buffer.bytes", 1048576);
            this.put("bootstrap.servers", "");
            //props.put("zookeeper.connect", zkConnect);
            this.put("group.id", "");
        }
    }
}
