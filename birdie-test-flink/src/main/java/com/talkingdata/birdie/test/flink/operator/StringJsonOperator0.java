package com.talkingdata.birdie.test.flink.operator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.util.Collector;

/**
 * Created by jian.wang on 2017/5/20.
 */
public class StringJsonOperator0 extends RichFlatMapFunction<String, JsonNode> implements Operatorable {

    private static ObjectMapper jsonMapper = new ObjectMapper();

    public void flatMap(String value, Collector<JsonNode> out) throws Exception {
        if (StringUtils.isNotEmpty(value)) {
            out.collect(jsonMapper.readTree(value));
        }
    }
}
