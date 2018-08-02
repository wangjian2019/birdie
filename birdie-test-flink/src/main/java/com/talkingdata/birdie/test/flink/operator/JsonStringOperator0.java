package com.talkingdata.birdie.test.flink.operator;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.util.Collector;

/**
 * Created by jian.wang on 2017/5/20.
 */
public class JsonStringOperator0 extends RichFlatMapFunction<JsonNode, String> implements Operatorable {

    public void flatMap(JsonNode value, Collector<String> out) throws Exception {
        if (value == null)
            return;
        out.collect(value.toString());
    }
}
