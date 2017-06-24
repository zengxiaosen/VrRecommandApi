package com.sohu.rdcinf.vr.utils;

import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;


/**
 * Created by zengxiaosen on 2017/6/22.
 */
public class JedisUtil {
    private org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

    private static String ip;
    private static Integer port;

    static {
        PropertyUtils.load("redis.properties");
        ip = PropertyUtils.getProperty("redis.ip");
        port = Integer.valueOf(PropertyUtils.getProperty("redis.port"));

    }

    private static class RedisUtilHolder{
        /**
         * 静态初始化器，由jvm来保证线程安全
         */
        private static JedisUtil instance = new JedisUtil();
    }

    public static JedisUtil getInstance(){
        return RedisUtilHolder.instance;
    }

    public Jedis getJedis(){
        Jedis jedis = null;
        int count = 0;
        do{
            try{
                jedis = JedisPoolUtil.getJedisPoolInstance(ip, port).getResource();

            }catch (Exception e){
                log.error("Get redis master failed!" , e);
            }
            count++;
        }while(jedis == null && count < Integer.valueOf(PropertyUtils.getProperty("redis.retryNum")));
        return jedis;
    }


    /**
     * 释放redis实例倒连接池
     */
    public void closeJedis(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }


}
