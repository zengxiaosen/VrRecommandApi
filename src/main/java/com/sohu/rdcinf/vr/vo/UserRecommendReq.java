package com.sohu.rdcinf.vr.vo;

import java.util.List;

/**
 * Created by zengxiaosen on 2017/6/23.
 */
public class UserRecommendReq {
    /**
     * 用户id(必填)
     */
    private String userId;
    /**
     * 返回条数，大于系统中可推荐总条数则返回系统中最大条数（必填）
     */
    private Integer nums;
    /**
     * 推荐内容id，数组形式（非必填）
     * 内容推荐黑名单
     */
    private List<String> blacklistItems;
    /**
     * 推荐标签，数组形式（非必填）
     * 标签推荐黑名单
     */
    private List<String> blacklistTag;
    /**
     * 推荐标签，数组形式（非必填）
     * 标签推荐白名单
     */
    private List<String> preferTag;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public List<String> getBlacklistItems() {
        return blacklistItems;
    }

    public void setBlacklistItems(List<String> blacklistItems) {
        this.blacklistItems = blacklistItems;
    }

    public List<String> getBlacklistTag() {
        return blacklistTag;
    }

    public void setBlacklistTag(List<String> blacklistTag) {
        this.blacklistTag = blacklistTag;
    }

    public List<String> getPreferTag() {
        return preferTag;
    }

    public void setPreferTag(List<String> preferTag) {
        this.preferTag = preferTag;
    }

    @Override
    public String toString() {
        return "UserRecommendReq{" +
                "userId='" + userId + '\'' +
                ", nums=" + nums +
                ", blacklistItems=" + blacklistItems +
                ", blacklistTag=" + blacklistTag +
                ", preferTag=" + preferTag +
                '}';
    }

}
