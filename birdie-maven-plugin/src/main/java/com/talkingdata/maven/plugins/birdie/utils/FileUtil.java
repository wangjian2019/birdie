package com.talkingdata.maven.plugins.birdie.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * schema parser
 *
 * @author jian.wang
 */
public class FileUtil {

    /**
     * 读取文件
     *
     * @param Path
     * @return
     */
    public static String readFile(String Path) {
        BufferedReader reader = null;
        StringBuilder res = new StringBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                res.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            LoggerUtil.getLog().error(e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LoggerUtil.getLog().error(e.getMessage(), e);
                }
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
    }

}
