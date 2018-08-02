package com.talkingdata.maven.plugins.birdie.templateparser;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.talkingdata.maven.plugins.birdie.constant.Setting;
import com.talkingdata.maven.plugins.birdie.utils.LoggerUtil;
import com.talkingdata.maven.plugins.birdie.utils.ParserUtil;

/**
 * Generate SourceOperator.java
 * 
 * @author jian.wang
 *
 */
public class SourceOperatorParser implements Parser {
	public void execute(ObjectNode parameters){
		STGroup templates = new STGroupFile(Setting.engine + "/kafkasource.stg");
		ST st = templates.getInstanceOf("kafkasource");
		String className = parameters.get("name").asText();
		String topic = parameters.get("topic").asText();
		String brokerList = parameters.get("brokerList").asText();
		String groupId = parameters.get("groupId").asText();
		st.add("packageName", Setting.packageName + ".operator");
		st.add("name", className);
		st.add("topic", topic);
		st.add("brokerList", brokerList);
		st.add("groupId", groupId);
		LoggerUtil.getLog().info(st.render());
		ParserUtil.write2Java(st, className, Setting.outPath + "/operator");
	}
}
