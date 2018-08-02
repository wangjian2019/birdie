package com.talkingdata.maven.plugins.birdie.utils;

import org.stringtemplate.v4.ST;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by jian.wang on 2017/5/20.
 */
public class ParserUtil {
    /**
     * 写入指定的文件
     *
     * @param st
     * @param className
     * @param outPath
     */
    public static void write2Java(ST st, String className, String outPath) {
        FileWriter out = null;
        try {
            File pd = new File(outPath);
            pd.mkdirs();
            out = new FileWriter(new File(pd, className + ".java"));
            out.append(st.render());
        } catch (IOException e) {
            LoggerUtil.getLog().error(e.getMessage(), e);
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                LoggerUtil.getLog().error(e.getMessage(), e);
            }
        }
    }
}
