package com.talkingdata.birdie.test.flink.operator;

import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer09;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

/**
 * Created by jian.wang on 2017/5/20.
 */
public class KafkaSinkOperator0 extends FlinkKafkaProducer09<String> implements Operatorable {

    public KafkaSinkOperator0() {
        super("172.20.6.1:9092,172.20.6.2:9092,172.20.6.3:9092", "hehe", new SimpleStringSchema());
    }

}
