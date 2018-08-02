package com.talkingdata.birdie.test.flink.commons;

import java.util.HashMap;
import java.util.List;

/**
 * Created by jian.wang on 2017/5/19.
 */
public class KV<K, V> {
    K key;
    V value;

    public KV() {}

    public KV(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public static HashMap toMap(List<KV> kvList) {
        HashMap map = new HashMap();
        for (KV kv : kvList) {
            map.put(kv.getKey(), kv.getValue());
        }
        return map;
    }
}
