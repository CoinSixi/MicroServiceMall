package com.micromall.userauth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.User;
import com.micromall.userauth.service.UserAuthService;
import com.micromall.userauth.util.RedisTokenManager;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kxh on 2019/4/23.
 */
@RestController
public class UserAuthController {
    @Autowired
    private UserAuthService userAuthService;
    
    @Autowired
    private RedisTokenManager redisTokenManager;
    private static Logger logger = LoggerFactory.getLogger(UserAuthController.class);
    @RequestMapping(value="/user-auth/login/{username}/{password}")
    public Object login(@PathVariable String username, @PathVariable String password, HttpServletRequest request){
    	
        ServerResponse<User> response = this.userAuthService.login(username, password);
        if (response.getData() != null) {
        //登录成功生成token并保存token
            //String userAgent = request.getHeader("user-agent");
            String token = this.redisTokenManager.getToken();
            response.setMsg(token);
        }
        return response;
        //return response;
    }
    @RequestMapping(value="/user-auth/login/{phone}")
    public Object loginWithPhone(@PathVariable String phone){
    	
        ServerResponse<User> response = this.userAuthService.loginWithPhone(phone);
        if (response.getData() != null) {
        //登录成功生成token并保存token
            //String userAgent = request.getHeader("user-agent");
            String token = this.redisTokenManager.getToken();
            response.setMsg(token);
        }
        return response;
    }
    
    @RequestMapping(value="/user-auth/register")
    public Object register(User user){
    	logger.debug(user.toString());
        return userAuthService.register(user);
    }
    @RequestMapping(value="/user-auth/sendMsg/register/{phone}")
    public Object sendMsg(@PathVariable String phone) throws Exception{
    	ServerResponse<String> response = userAuthService.checkPhone(phone);
    	if(response.getStatus()==0){
    		return ServerResponse.createByErrorMessage("用户已存在");
    	}
    	return userAuthService.sendMessage(phone);
    }
    @RequestMapping(value="/user-auth/sendMsg/login/{phone}")
    public Object checkSendMsg(@PathVariable String phone) throws Exception{
    	ServerResponse<String> response = userAuthService.checkPhone(phone);
    	if(response.getStatus()!=0){
    		return response;
    	}
    	return userAuthService.sendMessage(phone);
    }
    @RequestMapping(value="/user-auth/reset/{phone}/{password}")
    public Object resetPassword(@PathVariable String phone, @PathVariable String password) {
    
        return userAuthService.resetPassword(phone, password);
    }
    
    @RequestMapping(value="/user-auth/logout")
    public Object logout(HttpServletRequest request){
    	String token = request.getHeader("Authorization");
    	this.redisTokenManager.loginOff(token);
        return ServerResponse.createBySuccessMessage("退出登录");
    }
}
