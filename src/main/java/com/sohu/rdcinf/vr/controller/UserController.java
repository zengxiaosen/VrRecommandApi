package com.sohu.rdcinf.vr.controller;

import com.sohu.rdcinf.vr.service.UserService;
import com.sohu.rdcinf.vr.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zengxiaosen on 2017/6/23.
 */
@Controller
@RequestMapping("/")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private static final String NUMS = "nums";
    private static final String RESULTS = "results";

    @Resource
    private UserService userService;

    @RequestMapping(value = "json", method = RequestMethod.GET)
    @ResponseBody
    public Response<Map<String, Object>> printWelcome(){
        logger.info("json:hello word");
        Map<String, Object> result = new HashMap<>();
        result.put(RESULTS, userService.hello());
        return new Response<>(0, "success", result);

    }

    @RequestMapping(value = "getRedisUser/{keyId}", method = RequestMethod.GET)
    @ResponseBody
    public Response<Map<String, Object>> printUser(@PathVariable(value = "keyId") String keyId){
        logger.info("get redis user");
        Map<String, Object> result = new HashMap<>();
        result.put(NUMS, userService.getUser(keyId).size());
        result.put(RESULTS, userService.getUser(keyId));
        return new Response<>(0, "seccuss", result);
    }
}
