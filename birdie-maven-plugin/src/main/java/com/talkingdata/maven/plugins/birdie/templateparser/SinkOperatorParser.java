package com.talkingdata.maven.plugins.birdie.templateparser;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.talkingdata.maven.plugins.birdie.constant.Setting;
import com.talkingdata.maven.plugins.birdie.utils.LoggerUtil;
import com.talkingdata.maven.plugins.birdie.utils.ParserUtil;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 * Generate SinkOperator.java
 * 
 * @author jian.wang
 *
 */
public class SinkOperatorParser implements Parser {
	
    public void execute(ObjectNode parameters) {
        STGroup templates = new STGroupFile(Setting.engine + "/kafkasink.stg");
        ST st = templates.getInstanceOf("kafkasink");
        String className = parameters.get("name").asText();
        String brokerList = parameters.get("brokerList").asText();
        String topic = parameters.get("topic").asText();
        st.add("packageName", Setting.packageName + ".operator");
        st.add("name", className);
        st.add("topic", topic);
        st.add("brokerList", brokerList);
        LoggerUtil.getLog().info(st.render());
        ParserUtil.write2Java(st, className, Setting.outPath + "/operator");
    }
}
