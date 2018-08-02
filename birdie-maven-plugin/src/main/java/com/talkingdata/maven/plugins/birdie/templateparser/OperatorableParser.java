package com.talkingdata.maven.plugins.birdie.templateparser;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.talkingdata.maven.plugins.birdie.constant.Setting;
import com.talkingdata.maven.plugins.birdie.utils.LoggerUtil;
import com.talkingdata.maven.plugins.birdie.utils.ParserUtil;

/**
 * Operatorable, just an empty interface, used to expand its feature in the future.
 * So OperatorableParser just generates this empty interface.
 * 
 * @author jian.wang
 *
 */
public class OperatorableParser implements Parser {

	@Override
	public void execute(ObjectNode parameters) {
		STGroup templates = new STGroupFile(Setting.engine + "/operatorable.stg");
        ST st = templates.getInstanceOf("operatorable");
        st.add("packageName", Setting.packageName + ".operator");
        LoggerUtil.getLog().info(st.render());
        ParserUtil.write2Java(st, "Operatorable", Setting.outPath + "/operator");
	}

}
