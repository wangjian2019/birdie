package com.talkingdata.birdie.test.flink.operator;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.util.Collector;

/**
 * Created by jian.wang on 2017/5/20.
 */
public class ToJson extends RichFlatMapFunction<String,JsonNode> implements Operatorable {
    public String getType() {
        return null;
    }

    public void flatMap(String s, Collector<JsonNode> collector) throws Exception {
        collector.collect(null);
    }
}
