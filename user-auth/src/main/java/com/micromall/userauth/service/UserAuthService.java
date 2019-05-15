package com.micromall.userauth.service;

import java.util.List;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Order;
import com.micromall.apiservice.entities.Product;
import com.micromall.apiservice.entities.User;

public interface UserAuthService {
	ServerResponse<String> register(User user);
	ServerResponse<User> login(String username, String password);
	ServerResponse<User> loginWithPhone(String phone);

	ServerResponse<String> lostPassword(String phone);
	ServerResponse<String> resetPassword(String phone, String password);
	ServerResponse<String> logout(int userId);
	
	ServerResponse<String> checkPhone(String phone);
	ServerResponse<String> sendMessage(String phone);
	
}
