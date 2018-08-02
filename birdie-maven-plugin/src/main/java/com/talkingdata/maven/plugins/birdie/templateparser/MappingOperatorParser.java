package com.talkingdata.maven.plugins.birdie.templateparser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.talkingdata.maven.plugins.birdie.constant.Setting;
import com.talkingdata.maven.plugins.birdie.utils.LoggerUtil;
import com.talkingdata.maven.plugins.birdie.utils.ParserUtil;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.Map;

/**
 * Created by jian.wang on 2017/5/20.
 */
public class MappingOperatorParser implements Parser {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void execute(ObjectNode parameters) {
        STGroup templates = new STGroupFile(Setting.engine + "/mapping.stg");
        ST st = templates.getInstanceOf("mappingoperator");
        String className = parameters.get("name").asText();
        st.add("name", className);
        st.add("packageName", Setting.packageName + ".operator");
        st.add("srcfield", parameters.get("src").asText());
        st.add("distfield", parameters.get("dist").asText());
        st.add("args", objectMapper.convertValue(parameters.get("mapping"), Map.class).entrySet());
        LoggerUtil.getLog().info(st.render());
        ParserUtil.write2Java(st, className, Setting.outPath + "/operator");
    }
}
