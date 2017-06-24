package com.sohu.rdcinf.vr.service.impl;

import com.google.gson.Gson;
import com.sohu.rdcinf.vr.common.RecommendConstants;
import com.sohu.rdcinf.vr.dao.IRecommendDao;
import com.sohu.rdcinf.vr.model.Recommend;
import com.sohu.rdcinf.vr.service.RecommendService;
import com.sohu.rdcinf.vr.vo.RecItemResp;
import com.sohu.rdcinf.vr.vo.UserRecommendReq;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zengxiaosen on 2017/6/23.
 */
@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    private IRecommendDao recommendDao;

    @Override
    public List<RecItemResp> getPopItems(int period, int nums) {
        //将period转为redis中的key字符串
        String key = RecommendConstants.POP_ITEM_PREFIX + period + RecommendConstants.POP_ITEM_SUFFIX;
        List<String> strs = recommendDao.getList(key, nums);
        List<RecItemResp> res = convert(strs);
        return null;
    }

    //字符串数组反序列化并转换为List<RecItemResp>
    private List<RecItemResp> convert(List<String> strs) {
        Gson gson = new Gson();
        List<RecItemResp> res = new ArrayList<RecItemResp>();
        for(String str: strs){
            Recommend recommend = gson.fromJson(str,Recommend.class);
            RecItemResp tmp = new RecItemResp();
            tmp.setItemId(recommend.getVideo_id());
            tmp.setScore(recommend.getValue());
            res.add(tmp);
        }
        return res;
    }

    @Override
    public List<RecItemResp> recommendByItemID(String itemId, Integer nums) {
        //将itemId转换为redis中的key字符串
        String key = RecommendConstants.ITEM_PREFIX + itemId;
        List<String> strs = recommendDao.getList(key, nums);
        List<RecItemResp> res = convert(strs);
        return res;
    }

    @Override
    public List<RecItemResp> recommendByUserID(UserRecommendReq userRecommendReq) {
        String userId = userRecommendReq.getUserId();
        String cfKey = RecommendConstants.USER_CF_PREFIX + userId;
        String tagKey = RecommendConstants.USER_TAG_PREFIX + userId;
        List<String> cfStrs = recommendDao.getList(cfKey , RecommendConstants.USER_REC_LIMIT);
        List<String> tagStrs = recommendDao.getList(tagKey , RecommendConstants.USER_REC_LIMIT);
        //辅助数据结构 去重复
        Set<String> sup = new HashSet<String>();
        //合并、去重复、过滤请求条件
        cfStrs.addAll(tagStrs);
        Gson gson = new Gson();
        List<RecItemResp> res = new ArrayList<>();
        for(int i=0;i<cfStrs.size();){
            Recommend recommend = gson.fromJson(cfStrs.get(i) , Recommend.class);
            //1、包含 itemID 黑名单，remove
            if(userRecommendReq.getBlacklistItems() != null && userRecommendReq.getBlacklistItems().contains(recommend.getVideo_id())){
                cfStrs.remove(i);
                continue;
            }
            //2、标签黑名单，request信息和推荐信息tag项都不为空
            if(userRecommendReq.getBlacklistTag() != null && StringUtils.isNotBlank(recommend.getTag())){
                //包含 tag 黑名单，remove
                List<String> tags = Arrays.asList(recommend.getTag().split(","));
                //如果发现标签黑名单，并从cfStrs中删除该recommend项，应跳出上一层循环！！！
                int sizeFlag = cfStrs.size();
                for(String tag : userRecommendReq.getBlacklistTag()){
                    if(tags.contains(tag)){
                        cfStrs.remove(i);
                        continue;
                    }
                }
                if(cfStrs.size()<sizeFlag){
                    continue;
                }
            }
            //并集去重复
            if(!sup.contains(recommend.getVideo_id())){
                sup.add(recommend.getVideo_id());
            }else{
                //包含同一 video_id ，去重复
                cfStrs.remove(i);
                continue;
            }
            //3、优先 推荐 含有标签白名单中推荐项
            //有项目减少，再进行i++
            if(userRecommendReq.getPreferTag() != null && StringUtils.isNotBlank(recommend.getTag())){
                List<String> tags = Arrays.asList(recommend.getTag().split(","));
                //remove 与 continue 成对出现，过滤所有条件后，最后执行i++操作
                int sizeFlag = cfStrs.size();
                for(String tag : userRecommendReq.getPreferTag()){
                    if(tags.contains(tag)){
                        //包含标签白名单中的标签，作为结果集中的一项
                        RecItemResp item = new RecItemResp();
                        item.setItemId(recommend.getVideo_id());
                        item.setScore(recommend.getValue());
                        res.add(item);
                        //从原始结果集中删除该项目
                        cfStrs.remove(i);
                        continue;
                    }
                }
                if(cfStrs.size()<sizeFlag){
                    continue;
                }
            }
            i++;
        }
        //对结果集 数目进行处理
        if(res.size() >= userRecommendReq.getNums()){
            //优先结果集超出请求结果集范围，进行截取
            res = res.subList(0,userRecommendReq.getNums());
        }else{
            int index=0;
            //优先结果集小于请求结果集范围（推荐书目不够）border变量控制最终结果集范围！
            int border = userRecommendReq.getNums() <= cfStrs.size() ? userRecommendReq.getNums() : cfStrs.size();
            for(int i=res.size();i<border;i++){
                Recommend recommend = gson.fromJson(cfStrs.get(index) , Recommend.class);
                RecItemResp item = new RecItemResp();
                item.setItemId(recommend.getVideo_id());
                item.setScore(recommend.getValue());
                res.add(item);
                index++;
            }
        }
        return res;
    }



}
