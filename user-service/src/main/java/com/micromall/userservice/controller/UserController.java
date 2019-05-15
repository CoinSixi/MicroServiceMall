package com.micromall.userservice.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.User;
import com.micromall.userservice.service.IUserService;

import org.springframework.cloud.client.discovery.DiscoveryClient;

@RestController
public class UserController {
    
    @Autowired
    private IUserService iUserService;
    
    
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    
    /* 
     * 登陆功能
     * 访问地址为/user/login 访问方式为POST
     * 如果登陆成功，将用户放入到Session中
     */
    @RequestMapping(value = "/user/login/{username}/{password}")
    public ServerResponse<User> login(@PathVariable("username")  String username, @PathVariable("password") String password) {
        ServerResponse<User> response = iUserService.login(username, password);
        
        if (response.isSuccess()) {
            logger.info("user:"+username+" 登录成功！");
        }else {
        	logger.error("user:"+username+" "+response.getMsg());
        }
        return response;
    }
    
    @RequestMapping(value = "/user/login/{phone}")
    public ServerResponse<User> loginWithPhone(@PathVariable("phone")  String phone) {
        ServerResponse<User> response = iUserService.loginWithPhone(phone);
      
        if (response.isSuccess()) {
            logger.info("phone:"+phone+" 登录成功！");
        }else {
        	logger.error("phone:"+phone+" "+response.getMsg());
        }
        return response;
    }
    
    /*
     * 注册功能
     * 访问地址为/user/register,访问方式为POST
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
    public ServerResponse<String> register(@RequestBody User user){
    	//ServerResponse<User> response = iUserService.register(user);
         return ServerResponse.createBySuccessMessage(user.getUsername());
    }
    @RequestMapping(value = "/user/registers", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
    public ServerResponse<String> registers(User user){
    	//ServerResponse<User> response = iUserService.register(user);
         return ServerResponse.createBySuccessMessage(user.toString());
    }
    @RequestMapping(value = "/user/register1")
    public ServerResponse<User> register1(User user){
    	//ServerResponse<User> response = iUserService.register(user);
         return ServerResponse.createBySuccessMessage(user.getUsername());
    }
    @RequestMapping(value = "/user/register2", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
    public ServerResponse<User> register2(User user){
    	ServerResponse<User> response = iUserService.register(user);
         return response;
    }
    @RequestMapping(value = "/user/register3")
    public ServerResponse<User> register3(User user){
    	ServerResponse<User> response = iUserService.register(user);
         return response;
    }
    /*
     * 注销功能
     * 访问地址为/user/delete,访问方式为POST
     */
    @RequestMapping(value = "/user/delete")
    public ServerResponse<String> delete(User user){
         return iUserService.delete(user);
    }
    @RequestMapping(value = "/user/delete/{username}")
    public ServerResponse<String> delete(@PathVariable("username")  String username){
         return iUserService.deleteByUsername(username);
    }
    @RequestMapping(value = "/user/update", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
    public ServerResponse<User> update(@RequestBody User user){
    	ServerResponse<User> response = iUserService.update(user);
         return response;
    }
    @RequestMapping(value = "/user/reset/{phone}/{password}")
    public ServerResponse<String> resetPassword(@PathVariable("phone")  String phone, @PathVariable("password")  String password){
    	ServerResponse<String> response = iUserService.resetPassword(phone, password);
         return response;
    }
    @RequestMapping(value = "/user/getall")
    public Object getAllUsers() {
    	ServerResponse<List<User>> response = iUserService.getAllUsers();
        return response;
    }
    @RequestMapping(value = "/user/{id}")
    public ServerResponse<User> getUserById(@PathVariable("id")  int id) {
    	ServerResponse<User> response = iUserService.getUserById(id);
        return response;
    }
    
    @RequestMapping(value = "/user/checkPhone/{phone}")
    public ServerResponse<String> checkPhone(@PathVariable("phone")  String phone) {
    	ServerResponse<String> response = iUserService.checkPhone(phone);
        return response;
    }
}
