package com.sohu.rdcinf.vr.model;

import java.io.Serializable;

/**
 * Created by zengxiaosen on 2017/6/23.
 */
public class Recommend implements Serializable{
    private String video_id;
    private double value;
    private String tag;
    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Recommend{" +
                "video_id='" + video_id + '\'' +
                ", value=" + value +
                ", tag='" + tag + '\'' +
                '}';
    }
}
