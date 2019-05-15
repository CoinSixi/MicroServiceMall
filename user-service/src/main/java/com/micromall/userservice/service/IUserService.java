package com.micromall.userservice.service;

import java.util.List;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.User;

public interface IUserService {
    //登陆
    ServerResponse<User> login(String username, String password);
    ServerResponse<User> loginWithPhone(String phone);
    ServerResponse<User> register(User user);
    ServerResponse<String> delete(User user);
    ServerResponse<String> deleteByUsername(String username);
    ServerResponse<User> update(User user);
    ServerResponse<String> resetPassword(String phone, String password);
    ServerResponse<User> getUserById(int id);
    ServerResponse<List<User>> getAllUsers();
    ServerResponse<String> checkPhone(String phone);
    //ServerResponse<String> deleteByUsername(String username);
    
 }