package com.sohu.rdcinf.vr.common;

/**
 * Created by zengxiaosen on 2017/6/23.
 */
public class RecommendConstants {
    //返回结果
    public static final String NUMS = "nums";
    public static final String RESULT = "results";

    //缓存key前后缀
    public static final String POP_ITEM_PREFIX = "recommend:popular:";
    public static final String POP_ITEM_SUFFIX = " days";

    public static final String ITEM_PREFIX =  "recommend:item_cf:";

    public static final String USER_CF_PREFIX = "recommend:user_cf:";
    public static final String USER_TAG_PREFIX =  "recommend:user_tag:";

    //取全部缓存list的上限范围
    public static final int USER_REC_LIMIT = 1000;
}
