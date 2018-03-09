package com.jxliu.mybatis.dao;

import com.jxliu.mybatis.po.User;

public interface UserDao {
	public User findUserById(int id) throws Exception;
	public void insertUser(User user) throws Exception;
}
