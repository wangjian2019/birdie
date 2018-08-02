package com.talkingdata.maven.plugins.birdie;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.talkingdata.maven.plugins.birdie.constant.Setting;
import com.talkingdata.maven.plugins.birdie.utils.LoggerUtil;
import com.talkingdata.maven.plugins.birdie.utils.ParserUtil;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by jian.wang on 2017/5/20.
 */
public class FlowGenerate {

    public static void start(JsonNode parameters) {
        String source = null;
        String sink = null;
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < parameters.size(); i++) {
            ObjectNode objectNode = (ObjectNode) parameters.get(i);
            map.put(objectNode.get("name").asText(), objectNode.get("next").asText());
            String type = objectNode.get("type").asText();
            if ("source".equals(type)) {
                source = objectNode.get("name").asText();
            }
            if ("sink".equals(type)) {
                sink = objectNode.get("name").asText();
            }
        }
        List<String> operators = new ArrayList<>();
        String tem = map.get(source);

        while (tem != null && tem != "" && map.containsKey(tem)) {
            operators.add(tem);
            tem = map.get(tem);
        }
        LoggerUtil.getLog().info(operators.toString());
        LoggerUtil.getLog().info(tem);

        STGroup templates = new STGroupFile(Setting.engine + "/bootstrap.stg");
        ST st = templates.getInstanceOf("bootstrap");
        st.add("packageName", Setting.packageName);
        st.add("source", source);
        st.add("sink", sink);
        st.add("operators", operators);
        LoggerUtil.getLog().info(st.render());
        ParserUtil.write2Java(st, "Bootstrap", Setting.outPath);
    }
}
