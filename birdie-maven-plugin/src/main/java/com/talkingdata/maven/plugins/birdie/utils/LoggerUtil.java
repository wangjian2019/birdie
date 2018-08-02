package com.talkingdata.maven.plugins.birdie.utils;

import org.apache.maven.plugin.logging.Log;

/**
 * Created by jian.wang on 2017/5/20.
 */
public class LoggerUtil {
    private static Log log;

    public static void setLog(Log log) {
        LoggerUtil.log = log;
    }

    public static Log getLog() {
        return log;
    }
}
