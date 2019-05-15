package com.micromall.usercenter.service;

import java.util.List;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Order;
import com.micromall.apiservice.entities.Product;
import com.micromall.apiservice.entities.Shipping;
import com.micromall.apiservice.entities.User;

public interface UserCenterService {
	ServerResponse<User> getUserInfo(int userId);
	ServerResponse<User> writeUserInfo(User user);
	
	
}
