package com.sohu.rdcinf.vr.dao.impl;

import com.sohu.rdcinf.vr.dao.IRecommendDao;
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
public class RecommendDao implements IRecommendDao {
    private Logger logger = LoggerFactory.getLogger(RecommendDao.class);
    private Jedis jedis = JedisUtil.getInstance().getJedis();

    @Override
    public List<String> getList(String key, int nums) {
        List<String> keys = jedis.lrange(key, 0, nums);
        return keys;
    }
}
