package com.sohu.rdcinf.vr.vo;

/**
 * Created by zengxiaosen on 2017/6/23.
 */
public class RecItemResp {
    private String itemId;
    private double score;
    public String getItemId(){
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString(){
        return "RecItemResp{" +
                "itemId='" + itemId + '\'' +
                ", score=" + score +
                '}';
    }
}
