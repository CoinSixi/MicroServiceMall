package com.micromall.userservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micromall.apiservice.common.MD5Util;
import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.User;
import com.micromall.userservice.dao.UserMapper;

@Service("iUserService")
public class UserServiceImpl implements IUserService {
    //注入userMapper
    @Autowired
    private UserMapper userMapper;
    /*
     * 登陆
     * checkUsername()和checkNamePassword()检查用户名密码是否正确
     * resultCount=1表示存在,resultCount=0表示不存在
     * 设置登录时间，将获取的User密码设置为空
     * 返回含有User数据的ServerResponse
     * @see com.mall.MicroServiceMall.service.IUserService#login(java.lang.String, java.lang.String)
     */
    @Override
    public ServerResponse<User> login(String username, String password) {
    	int checkUsername = userMapper.checkUsername(username);
    	String md5Password = MD5Util.string2MD5(password);
    	int checkUser = userMapper.checkNamePassword(username, md5Password);
        if (checkUsername == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        
        if (checkUser == 0) {
            return ServerResponse.createByErrorMessage("密码错误");
            
        }
        userMapper.setLoginTime(username);
        User user = userMapper.getUserByName(username);
        //将密码设置为空
        user.setPassword("");
        return ServerResponse.createBySuccess("登录成功", user);
    }
    
    @Override
    public ServerResponse<User> loginWithPhone(String phone) {
    	int checkPhone = userMapper.checkPhone(phone);
        if (checkPhone == 0) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        User userTemp = userMapper.getUserByPhone(phone);
        userMapper.setLoginTime(userTemp.getUsername());
        User user = userMapper.getUserById(userTemp.getId());
        user.setPassword("");
        //将密码设置为空
        return ServerResponse.createBySuccess("登录成功", user);
    }
    /*
     * 注册
     * 检查username和phone
     * 加密密码插入用户
     * 返回插入信息
     * @see com.mall.MicroServiceMall.service.IUserService#register(com.mall.MicroServiceMall.pojo.User)
     */
    @Override
    public ServerResponse<User> register(User user) {
        ServerResponse<String> vaildResponse = this.checkValid(user.getUsername(),"USERNAME");
        //校验用户名
        if(!vaildResponse.isSuccess()){
            return ServerResponse.createByErrorMessage(vaildResponse.getMsg());
        }
        //校验电话
        vaildResponse = this.checkValid(user.getPhone(),"PHONE");
        if(!vaildResponse.isSuccess()){
        	return ServerResponse.createByErrorMessage(vaildResponse.getMsg());
        }
        //MD5加密
        user.setPassword(MD5Util.string2MD5(user.getPassword()));
        //插入用户
        //int resultCount = userMapper.insert(user);
        int resultCount = userMapper.insertSelective(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    	 /*int resCount = userMapper.checkUsername(user.getUsername());
         if(resCount == 0 ){
             return ServerResponse.createByErrorMessage("用户不存在!");
         }
         userMapper.updateByPrimaryKeySelective(user);
         userMapper.setUpdateTime(user.getUsername());
         User resUser = userMapper.getUserByName(user.getUsername());
         //将密码设置为空
         resUser.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
 		return ServerResponse.createBySuccessMessage("注册成功");*/
    }
    /*
     * 检验数据库是否存在username和phone
     */
    public ServerResponse<String> checkValid(String str,String type){
        if(org.apache.commons.lang3.StringUtils.isNotBlank(type)){  //type不是空，才开始校验
           //开始校验
           if("USERNAME".equals(type)){
                //判断用户名
                int resultCount = userMapper.checkUsername(str);
                if(resultCount > 0 ){
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            //判断phone
            if("PHONE".equals(type)){
                 int resultCount = userMapper.checkPhone(str);
                 System.out.println("phone"+str);
                 if(resultCount > 0 ){
                     return ServerResponse.createByErrorMessage("phone已存在");
                 }
            }
        }else{
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
}
    /*
     * 传入User user删除user
     * 检查username是否存在
     * 存在删除用户，不存在返回不存在ServerResponse
     * @see com.mall.MicroServiceMall.service.IUserService#delete(com.mall.MicroServiceMall.pojo.User)
     */
	@Override
	public ServerResponse<String> delete(User user) {
		// TODO Auto-generated method stub
		int resultCount = userMapper.checkUsername(user.getUsername());
        if(resultCount == 0 ){
            return ServerResponse.createByErrorMessage("用户不存在!");
        }
		userMapper.delete(user);
		return ServerResponse.createBySuccessMessage("删除成功!");
	}
	/*
     * 传入String username删除user
     * 检查username是否存在
     * 存在删除用户，不存在返回不存在ServerResponse
     * @see com.mall.MicroServiceMall.service.IUserService#deleteByUsername(String)
     */
	@Override
	public ServerResponse<String> deleteByUsername(String username) {
		// TODO Auto-generated method stub
		int resultCount = userMapper.checkUsername(username);
        if(resultCount == 0 ){
            return ServerResponse.createByErrorMessage("用户不存在!");
        }
		userMapper.deleteByUsername(username);
		return ServerResponse.createBySuccessMessage("删除成功!");
	}
	/*
     * 传入User user更新用户信息
     * 检查username是否存在
     * 存在更新用户，设置更新时间
     * 返回更新后的User数据
     * @see com.mall.MicroServiceMall.service.IUserService#update(com.mall.MicroServiceMall.pojo.User)
     */
	@Override
	public ServerResponse<User> update(User user) {
		// TODO Auto-generated method stub
        int resCount = userMapper.checkUsername(user.getUsername());
        if(resCount == 0 ){
            return ServerResponse.createByErrorMessage("用户不存在!");
        }
        userMapper.updateByPrimaryKeySelective(user);
        userMapper.setUpdateTime(user.getUsername());
        User resUser = userMapper.getUserByName(user.getUsername());
        //将密码设置为空
        resUser.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
		return ServerResponse.createBySuccess("更新成功", resUser);
	}
	/*
	 * 重置密码
	 * @see com.micromall.userservice.service.IUserService#update(com.micromall.apiservice.entities.User)
	 */
	@Override
	public ServerResponse<String> resetPassword(String phone, String password) {
		// TODO Auto-generated method stub
        int resCount = userMapper.checkPhone(phone);
        if(resCount == 0 ){
            return ServerResponse.createByErrorMessage("用户不存在!");
        }
        User user = userMapper.getUserByPhone(phone);
        user.setPhone(phone);
        user.setPassword(MD5Util.string2MD5(password));
        userMapper.updateByPrimaryKeySelective(user);
        userMapper.setUpdateTime(user.getUsername());
		return ServerResponse.createBySuccess("更新成功");
	}
	/*
     * 获取全部用户信息
     * 返回含有List<User>数据的ServerResponse
     * @see com.mall.MicroServiceMall.service.IUserService#getAllUsers()
     */
	@Override
	public ServerResponse<List<User>> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> userList = new ArrayList<User>();
		userList = userMapper.getUserList();
		return ServerResponse.createBySuccess("获取成功", userList);
	}
	
	@Override
	public ServerResponse<User> getUserById(int id) {
		// TODO根据ID返回user
		User user = userMapper.getUserById(new Integer(id));
		if(user==null){
			return ServerResponse.createByErrorMessage("用户不存在!");
		}
		user.setPassword("");
		return ServerResponse.createBySuccess("success", user);
	}

	@Override
	public ServerResponse<String> checkPhone(String phone) {
		// TODO Auto-generated method stub
		int resCount = userMapper.checkPhone(phone);
		if(resCount == 0 ){
            return ServerResponse.createByErrorMessage("用户不存在!");
        }
		return ServerResponse.createBySuccessMessage("用户存在!");
	}
 }
