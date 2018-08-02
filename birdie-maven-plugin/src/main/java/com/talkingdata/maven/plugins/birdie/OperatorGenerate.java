package com.talkingdata.maven.plugins.birdie;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.talkingdata.maven.plugins.birdie.templateparser.*;

/**
 * Created by jian.wang on 2017/5/20.
 */
public class OperatorGenerate {

    public static void start(JsonNode jsonArray) {
        new OperatorableParser().execute(null);
        for (int i = 0; i < jsonArray.size(); i++) {
            ObjectNode jsonObject = (ObjectNode) jsonArray.get(i);
            String type = jsonObject.get("type").asText();
            Parser parser = null;
            switch (type) {
                case "source":
                    parser = new SourceOperatorParser();
                    break;
                case "sink":
                    parser = new SinkOperatorParser();
                    break;
                case "ExtractOperator":
                    parser = new ExtractOperatorParser();
                    break;
                case "StringJsonOperator":
                    parser = new StringJsonOpParser();
                    break;
                case "JsonStringOperator":
                    parser = new JsonStringOpParser();
                    break;
                case "MappingOperator":
                    parser = new MappingOperatorParser();
                    break;
            }
            if (parser == null) {
                continue;
            }
            parser.execute(jsonObject);
        }
    }
}
