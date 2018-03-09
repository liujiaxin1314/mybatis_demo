package com.jxliu.mybatis.mapper;

import java.util.List;

import com.jxliu.mybatis.po.User;

/**
 * 用户管理mapper
 */
public interface UserMapper{
	//根据用户id查询用户信息
	public User getUserById(int id) throws Exception;
	//查询用户列表
	public List<User> getUserByName(String username) throws Exception;
	//添加用户信息
	public void insertUser(User user)throws Exception; 
}

