package com.sohu.rdcinf.vr.service.impl;

import com.sohu.rdcinf.vr.dao.IUserDao;
import com.sohu.rdcinf.vr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zengxiaosen on 2017/6/23.
 */
public class UserServiceImpl implements UserService{

    private final IUserDao userDao;
    @Autowired
    public UserServiceImpl(IUserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public String hello() {
        return "hello";
    }

    @Override
    public List<String> getUser(String keyId) {
        List<String> users = userDao.getList(keyId);
        return users;
    }
}
