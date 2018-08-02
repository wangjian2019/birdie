package com.talkingdata.birdie.test.flink;

import com.talkingdata.birdie.test.flink.operator.*;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;


/**
 * Created by jian.wang on 2017/5/19.
 */
public class Bootstrap {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = getEnvironment();
        DataStream stream = env.addSource(new KafkaSourceOperator0());
        stream = stream.flatMap(new StringJsonOperator0());
        stream = stream.flatMap(new MappingOperator());
        stream = stream.flatMap(new ExtractOperator0());
        stream = stream.flatMap(new JsonStringOperator0());
        stream.addSink(new KafkaSinkOperator0());

        env.execute("BirdieSelfTest");
    }


    public static StreamExecutionEnvironment getEnvironment() {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        return env;
    }

}
