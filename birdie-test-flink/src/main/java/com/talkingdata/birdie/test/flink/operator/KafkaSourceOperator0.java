package com.talkingdata.birdie.test.flink.operator;

import com.talkingdata.birdie.test.flink.commons.KV;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by jian.wang on 2017/5/20.
 */
public class KafkaSourceOperator0 extends FlinkKafkaConsumer09<String> implements Operatorable {

    private static Properties props = new Properties();

    static {
        List<KV<String, String>> kvList = Arrays.asList(new KV<String, String>("group.id", "test-group"),
                new KV<String, String>("bootstrap.servers", "172.20.6.1:9092,172.20.6.2:9092,172.20.6.3:9092"));
        for (KV kv : kvList) {
            props.put(kv.getKey(), kv.getValue());
        }
    }

    public KafkaSourceOperator0() {
        super("analytics_game_etl_install", new SimpleStringSchema(), props);
    }


}
