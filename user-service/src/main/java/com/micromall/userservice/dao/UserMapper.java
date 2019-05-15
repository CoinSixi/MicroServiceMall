package com.micromall.userservice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.micromall.apiservice.entities.User;



@Mapper
public interface UserMapper {
	//插入用户
    int insert(User record);
    int insertSelective(User record);

	//更新数据
	int updateByPrimaryKey(User record);
	int updateByPrimaryKeySelective(User record);
	
	//更新时间
	int setLoginTime(String username);
	int setUpdateTime(String username);
	
	//删除用户
	int delete(User record);
	int deleteByUsername(String username);
	
	//获取用户数据
	User getUserByName(String username);
	User getUserById(Integer id);
	User getUserByPhone(String phone);
	List<User> getUserList();
	
	//验证用户名、电话、密码
	int checkUsername(String username);
	int checkPhone(String phone);
	int checkNamePassword(@Param("username") String username, @Param("password") String password);
	
	
	
}