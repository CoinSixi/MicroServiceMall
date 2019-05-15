package com.micromall.usercenter.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Order;
import com.micromall.apiservice.entities.Product;
import com.micromall.apiservice.entities.Shipping;
import com.micromall.apiservice.entities.User;

@Service("UserCenterService")
public class UserCenterServiceImpl implements UserCenterService {

	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public ServerResponse<User> getUserInfo(int userId) {
		// TODO 根据userID获取用户信息
		ServerResponse<User> user = restTemplate.getForObject("http://localhost:8084/user/{1}", ServerResponse.class, userId);
		return user;
	}

	@Override
	public ServerResponse<User> writeUserInfo(User user) {
		// TODO Auto-generated method stub
		
		return restTemplate.postForObject("http://localhost:8084/user/update", user, ServerResponse.class);
	}
	

}
