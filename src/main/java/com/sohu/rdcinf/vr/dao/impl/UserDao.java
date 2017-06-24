package com.sohu.rdcinf.vr.dao.impl;

import com.sohu.rdcinf.vr.dao.IUserDao;
import com.sohu.rdcinf.vr.model.User;
import com.sohu.rdcinf.vr.utils.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by zengxiaosen on 2017/6/23.
 */
@Repository
public class UserDao implements IUserDao{
    private Logger logger = LoggerFactory.getLogger(UserDao.class);
    private Jedis jedis = JedisUtil.getInstance().getJedis();


    @Override
    public boolean add(User user) {
        return false;
    }

    @Override
    public boolean add(List<User> list) {
        return false;
    }

    /**
     * 删除单个
     * @param key
     */
    @Override
    public void delete(String key) {
        if(jedis.exists(key)){
            jedis.del(key);
        }else{
            System.out.println("No such key in redis");
        }
    }



    @Override
    public void delete(List<String> keys) {

    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean get(String keyId) {
        boolean result = false;
        if(jedis.exists(keyId)){
            result = true;
            String msg = jedis.get(keyId);
            System.out.println(msg);
        }else{
            System.out.println("No such key in redis");
        }
        return result;
    }

    /**
     * 通过key获取
     * @param keyId
     * @return是否成功
     */
    @Override
    public List<String> getList(String keyId) {
        List<String> list = jedis.lrange(keyId, 0, 10);
        if(list.size() != 0){
            list.forEach(System.out::println);
        }
        return list;
    }
}
