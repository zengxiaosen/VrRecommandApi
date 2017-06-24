package com.sohu.rdcinf.vr.dao;

import java.util.List;

/**
 * Created by zengxiaosen on 2017/6/23.
 */
public interface IRecommendDao {
    /**
     * 通过key获取
     */
    List<String> getList(String key, int nums);
}
