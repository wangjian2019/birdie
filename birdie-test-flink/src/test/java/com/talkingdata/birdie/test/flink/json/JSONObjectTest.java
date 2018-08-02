package com.talkingdata.birdie.test.flink.json;

import org.json.JSONObject;

/**
 * Created by xiaoqiang on 2017/5/20.
 */

public class JSONObjectTest {

    public static void main(String[] args){
        JSONObject jsonObject = new JSONObject("{\"name\":\"haha\", \"age\":11}");
        System.out.println(jsonObject.toString());
    }
}
