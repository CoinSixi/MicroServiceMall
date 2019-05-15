package com.micromall.userauth.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.micromall.apiservice.entities.User;
import java.util.UUID;

/**
 * Created by zxc on 2019/1/25.
 */
@Service("redisTokenManager")
public class RedisTokenManager implements TokenManager {
    @Value("${spring.redis.expire}")
    private Integer expire;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 创建token
     * @param 
     * @return
     */
    public String getToken(){
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        
        return token;
    }

    /**
     * 刷新用户
     * @param token
     */
    public void refreshUserToken(String token){
        Object userInfo=redisUtils.get(token);
        if(userInfo!=null){
            redisUtils.set(token,userInfo,3600);
        }
    }

    /**
     * 用户退出登陆
     * @param token
     */
    public void loginOff(String token){
        redisUtils.del(token);
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    public User getUserInfoByToken(String token){
    	System.out.println(redisUtils.hasKey(token));
        Object user=redisUtils.get(token);
        if(user!=null){
            return (User)user;
        }
        return null;
    }
}