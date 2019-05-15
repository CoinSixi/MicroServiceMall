package com.micromall.userauth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.User;
import com.micromall.userauth.util.QueryUtil;

@Service("UserAuthService")
public class UserAuthServiceImpl implements UserAuthService {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public ServerResponse<String> register(User user) {
		// TODO 注册功能
		//ServerResponse<String> response = restTemplate.postForObject("http://localhost:8084/user/register", user, ServerResponse.class);
		return ServerResponse.createBySuccessMessage(user.toString());
	}

	@Override
	public ServerResponse<User> login(String username, String password) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap();
		map.put("username", username);
		map.put("password", password);
		ServerResponse<User> response = restTemplate.getForObject("http://localhost:8084/user/login/{username}/{password}", ServerResponse.class, map);
		return response;
	}
	
	@Override
	public ServerResponse<User> loginWithPhone(String phone) {
		// TODO Auto-generated method stub
		ServerResponse<User> response = restTemplate.getForObject("http://localhost:8084/user/login/{1}", ServerResponse.class, phone);
		return response;
	}

	@Override
	public ServerResponse<String> lostPassword(String phone) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ServerResponse<String> resetPassword(String phone, String password) {
		// TODO 注册功能
		Map<String, String> map = new HashMap();
		map.put("phone", phone);
		map.put("password", password);
		ServerResponse<String> response = restTemplate.getForObject("http://localhost:8084/user/reset/{phone}/{password}", ServerResponse.class, map);
		return response;
	}
	@Override
	public ServerResponse<String> logout(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerResponse<String> sendMessage(String phone) {
		// TODO Auto-generated method stub
		String templateid = "461878";
		String param = QueryUtil.getRandNum();
		String mobile = phone;
		String uid = phone;
		String result = QueryUtil.sendSms(templateid, param, mobile, uid);
		JSONObject o = JSONObject.parseObject(result);
		o.put("param", param);
		return ServerResponse.createBySuccessMessage(param);
	}

	@Override
	public ServerResponse<String> checkPhone(String phone) {
		// TODO Auto-generated method stub
		ServerResponse<String> response = restTemplate.getForObject("http://localhost:8084/user/checkPhone/{1}", ServerResponse.class, phone);
		return response;
	}
	


}
