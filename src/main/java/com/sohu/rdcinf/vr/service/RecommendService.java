package com.sohu.rdcinf.vr.service;

import com.sohu.rdcinf.vr.vo.RecItemResp;
import com.sohu.rdcinf.vr.vo.UserRecommendReq;

import java.util.List;

/**
 * Created by zengxiaosen on 2017/6/23.
 */
public interface RecommendService {
    List<RecItemResp> getPopItems(int period, int nums);

    List<RecItemResp> recommendByItemID(String itemId, Integer nums);

    List<RecItemResp> recommendByUserID(UserRecommendReq userRecommendReq);

}
