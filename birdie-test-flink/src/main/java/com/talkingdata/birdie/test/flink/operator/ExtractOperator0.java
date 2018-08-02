package com.talkingdata.birdie.test.flink.operator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.MapUtils;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.util.Collector;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jian.wang on 2017/5/20.
 */
public class ExtractOperator0 extends RichFlatMapFunction<JsonNode, JsonNode> implements Operatorable {

    private static ObjectMapper jsonMapper = new ObjectMapper();
    private static Map<String, String> extractMap = createExtractMap();

    private static Map<String, String> createExtractMap() {
        List<KV> kvList = Arrays.asList(new KV("offset", "user.offset"), new KV("packageName", "app.appPackageName"),
                new KV("productId", "app.productid"), new KV("platformId", "app.platformid"));
        return KV.toMap(kvList);
    }

    public void flatMap(JsonNode value, Collector<JsonNode> out) throws Exception {
        if (value == null)
            return;
        out.collect(extract(value));
    }

    public JsonNode extract(JsonNode value) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (MapUtils.isNotEmpty(extractMap)) {
            for (Map.Entry<String, String> entry : extractMap.entrySet()) {
                String[] paths = entry.getValue().split("\\.");
                Object srcObj = null;
                JsonNode currentJsonObj = value;
                for (int i=0; i < paths.length ; i++) {
                    if (i == paths.length-1) {
                        srcObj = currentJsonObj.get(paths[i]);
                    } else {
                        if (currentJsonObj == null)
                            break;
                        currentJsonObj = currentJsonObj.get(paths[i]);
                    }
                }
                if (srcObj != null)
                    map.put(entry.getKey(), srcObj);
            }
        }
        try {
            return jsonMapper.readTree(jsonMapper.writeValueAsString(map));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class KV {
        public String key;
        public String value;

        public KV(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public static Map<String, String> toMap(List<KV> kvList) {
            Map<String, String> map = new HashMap<>();
            for (KV kv : kvList) {
                map.put(kv.key, kv.value);
            }
            return map;
        }
    }
}
