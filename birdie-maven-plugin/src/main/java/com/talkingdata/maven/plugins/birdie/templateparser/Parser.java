package com.talkingdata.maven.plugins.birdie.templateparser;


import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * All parser class should inherit the interface.
 * 
 * @author jian.wang
 *
 */
public interface Parser {
    void execute(ObjectNode parameters);
}
