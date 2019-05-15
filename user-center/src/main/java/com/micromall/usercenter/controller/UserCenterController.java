package com.micromall.usercenter.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Order;
import com.micromall.apiservice.entities.Product;
import com.micromall.apiservice.entities.User;
import com.micromall.usercenter.service.FtpUtil;
import com.micromall.usercenter.service.UserCenterService;

@RestController
public class UserCenterController {

	@Autowired
	UserCenterService userCenterService;
	
	private static Logger logger = LoggerFactory.getLogger(UserCenterController.class);
	
	@RequestMapping(value="/user-center/user/{id}")
	public Object getUserById(@PathVariable("id")  int id) {
    	ServerResponse<User> response = userCenterService.getUserInfo(id);
        return response;
    }
	
	@RequestMapping(value="/user-center/user/update")
	public Object updateUserInfo(User user, MultipartFile file) {
    	ServerResponse<User> response = userCenterService.writeUserInfo(user);
        return response;
    }
	
	@RequestMapping(value="/user-center/user/update1")
	public Object updateUserInfo1(User user, MultipartFile Image) throws IOException {
    	user.setUserImage(Image.getName());
    	System.out.println("sdvsdvdvs！！！！"+Image.getName());
    	FtpUtil.uploadFile("localhost", 9090, "ftpuser", "321duibuqi", "", "", "2.jpg", Image.getInputStream());
    	
        return Image.getContentType()+user.getUsername();
    }
	@RequestMapping(value="/user-center/user/update2")
	public boolean updateUserInfo2(){
		System.out.println("sdvsdvdvs！！！！");
		boolean u = FtpUtil.downloadFile("localhost", 9090, "ftpuser", "321duibuqi", "", "1.jpg", "D:/image-server");
        return u;
    }
}
