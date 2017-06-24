package com.sohu.rdcinf.vr.dao;

import com.sohu.rdcinf.vr.model.User;

import java.util.List;


/**
 * Created by zengxiaosen on 2017/6/23.
 */
public interface IUserDao {
    /**
     * 新增
     * @param user
     * @return
     */
    boolean add(User user);

    /**
     * 批量新增,使用pipeline方式
     * @param list
     * @return
     */
    boolean add(List<User> list);

    /**
     * 删除
     * @param key
     */
    void delete(String key);

    /**
     * 删除多个
     * @param keys
     */
    void delete(List<String> keys);

    /**
     * 修改
     * @param user
     * @return
     */
    boolean update(User user);

    /**
     * 通过key获取
     * @param keyId
     * @return
     */
    boolean get(String keyId);

    /**
     * 通过key获取
     * @param keyId
     * @return
     */
    List<String> getList(String keyId);


}
