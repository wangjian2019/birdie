package com.talkingdata.maven.plugins.birdie.templateparser;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.talkingdata.maven.plugins.birdie.constant.Setting;
import com.talkingdata.maven.plugins.birdie.utils.LoggerUtil;
import com.talkingdata.maven.plugins.birdie.utils.ParserUtil;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 * Created by jian.wang on 2017/5/20.
 */
public class StringJsonOpParser implements Parser {

    @Override
    public void execute(ObjectNode parameters) {
        STGroup templates = new STGroupFile(Setting.engine + "/stringjson.stg");
        ST st = templates.getInstanceOf("stringjson");
        st.add("name", parameters.get("name").asText());
        st.add("packageName", Setting.packageName + ".operator");
        LoggerUtil.getLog().info(st.render());
        ParserUtil.write2Java(st, parameters.get("name").asText(), Setting.outPath + "/operator");
    }
}
