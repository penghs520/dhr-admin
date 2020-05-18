package com.qinjee.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qinjee.admin.config.redis.RedisClusterService;
import com.qinjee.admin.exception.ExceptionCast;
import com.qinjee.admin.model.SystemConstants;
import com.qinjee.admin.model.ResultCode;
import com.qinjee.admin.model.UserSession;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @Autowired
    protected HttpServletRequest request;
    private UserSession userSession;
    @Autowired
    private RedisClusterService redisClusterService;
    protected final UserSession getSession(){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if ("SESSION_KEY".equals(cookies[i].getName())) {
                    userSession = new UserSession();
                    JSONObject jsonObject = JSON.parseObject(redisClusterService.get(cookies[i].getValue()));
                    if(null==jsonObject){
                        //session 不存在 ，则登录超时或未登录
                        ExceptionCast.cast(ResultCode.INVALID_SESSION);
                    }
                    //每次调用session后，再将session的过期时间重置
                    redisClusterService.expire(cookies[i].getValue(), SystemConstants.SESSION_TIME_OUT);
                    userSession.setUserId(Integer.valueOf(String.valueOf(jsonObject.get("userId"))));
                    userSession.setUserName(String.valueOf(jsonObject.get("userName")));
                    userSession.setPhone(String.valueOf(jsonObject.get("phone")));
                    userSession.setEmail(String.valueOf(jsonObject.get("email")));
                    return userSession;
                }
            }
        }
        //session 不存在 ，则登录超时或未登录
        ExceptionCast.cast(ResultCode.INVALID_SESSION);
        return null;
    }
}
