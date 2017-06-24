package com.sohu.rdcinf.vr.utils;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by zengxiaosen on 2017/6/22.
 */
public class JedisPoolUtil {
    private volatile static JedisPool pool;
    private volatile static Map<String, JedisPool> pools = new HashMap<String, JedisPool>();
    private JedisPoolUtil(){

    }

    public static JedisPool getJedisPoolInstance(String ip, int port){
        String key = ip + ":" + port;

        if(!pools.containsKey(key)){
            synchronized (JedisPoolUtil.class){
                if(!pools.containsKey(key)){
                    JedisPoolConfig config = new JedisPoolConfig();
                    config.setMinIdle(Integer.valueOf(PropertyUtils.getProperty("redis.minIdle","redis.properties")));
                    config.setMaxTotal(Integer.valueOf(PropertyUtils.getProperty("redis.maxTotal", "redis.properties")));
                    config.setMaxIdle(Integer.valueOf(PropertyUtils.getProperty("redis.maxIdle", "redis.properties")));
                    config.setMaxWaitMillis(Integer.valueOf(PropertyUtils.getProperty("redis.maxWaitMillis", "redis.properties")));
                    config.setTestOnBorrow(Boolean.valueOf(PropertyUtils.getProperty("redis.testOnBorrow", "redis.properties")));

                    pool = new JedisPool(config, ip, port);
                    pools.put(key, pool);
                }
            }
        }
        return pools.get(key);
    }


}
