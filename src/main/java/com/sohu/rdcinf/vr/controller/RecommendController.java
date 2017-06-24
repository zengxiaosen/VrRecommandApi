package com.sohu.rdcinf.vr.controller;

import com.sohu.rdcinf.vr.common.RecommendConstants;
import com.sohu.rdcinf.vr.service.RecommendService;
import com.sohu.rdcinf.vr.utils.Response;
import com.sohu.rdcinf.vr.vo.RecItemResp;
import com.sohu.rdcinf.vr.vo.UserRecommendReq;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zengxiaosen on 2017/6/23.
 */
@Controller
@RequestMapping("/recommend/vr")
public class RecommendController {
    private Logger logger = LoggerFactory.getLogger(RecommendController.class);
    @Autowired
    private RecommendService recommendService;

    /**
     * 根据用户进行推荐
     */
    @RequestMapping(value = "user", method = RequestMethod.POST)
    @ResponseBody
    public Response<Map<String, Object>> recommendByUserID(@RequestBody UserRecommendReq userRecommendReq){
        if(StringUtils.isBlank(userRecommendReq.getUserId())){
            return new Response<>(-1, "paramater errors", null);
        }
        if(userRecommendReq.getNums()==null || userRecommendReq.getNums()<=0){
            userRecommendReq.setNums(10);
        }
        Map<String, Object> result = new HashMap<>();
        List<RecItemResp> res = recommendService.recommendByUserID(userRecommendReq);
        result.put(RecommendConstants.NUMS, res.size());
        result.put(RecommendConstants.RESULT, res);
        return new Response<>(0, "seccuss", result);
    }

    /**
     * 根据项目进行推荐
     * @param itemId 项目id
     * @param nums 返回条数，大于系统中可推荐总条数则返回系统中最大条数
     * @return
     */
    @RequestMapping(value = "item", method = RequestMethod.GET)
    @ResponseBody
    public Response<Map<String, Object>> recommendByItemID(String itemId, Integer nums){
        if(StringUtils.isBlank(itemId)){
            return new Response<>(-1, "paramater errors", null);
        }
        if(nums==null || nums<=0){
            nums = 10;
        }
        Map<String, Object> result = new HashMap<>();
        List<RecItemResp> res = recommendService.recommendByItemID(itemId,nums);
        result.put(RecommendConstants.NUMS, res.size());
        result.put(RecommendConstants.RESULT, res);
        return new Response<>(0, "seccuss", result);
    }

    @RequestMapping(value = "item/pop", method = RequestMethod.GET)
    @ResponseBody
    public Response<Map<String, Object>> recommendByItemPop(Integer period, Integer nums){
        if(period==null || period<=0){
            period=200;
        }
        if(nums==null || nums<=0){
            nums=20;
        }
        Map<String, Object> result = new HashMap<>();
        List<RecItemResp> res = recommendService.getPopItems(period, nums);
        result.put(RecommendConstants.NUMS,res.size());
        result.put(RecommendConstants.RESULT,res);
        return new Response<>(0, "seccuss", result);
    }
}
