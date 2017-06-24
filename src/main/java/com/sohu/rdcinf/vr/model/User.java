package com.sohu.rdcinf.vr.model;

import java.io.Serializable;

/**
 * Created by zengxiaosen on 2017/6/23.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 6941787388127356730L;
    private String id;
    private String name;
    private String password;
    public User() {
    }

    public User(String id, String name, String password) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
    }

    /**
     * 获得id
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获得name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获得password
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置password
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
