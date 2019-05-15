package com.micromall.userauth.util;

import com.micromall.apiservice.entities.User;

public interface TokenManager {
    /**
     * 创建token
     * @param userInfo
     * @return
     */
    String getToken();

    /**
     * 刷新用户
     * @param token
     */
    void refreshUserToken(String token);

    /**
     * 用户退出登陆
     * @param token
     */
    void loginOff(String token);

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    User getUserInfoByToken(String token);
}
