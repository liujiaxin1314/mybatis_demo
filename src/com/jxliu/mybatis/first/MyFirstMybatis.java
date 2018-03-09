package com.jxliu.mybatis.first;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.jxliu.mybatis.po.User;

public class MyFirstMybatis {
	//会话工厂
	private SqlSessionFactory sqlSessionFactory = null;
	
	/**
	 * 每次启动之前先执行，获取一个sqlSessionFactory会话工厂 
	 * @throws IOException
	 */
	@Before
	public void createSqlSessionFactory() throws IOException{
		// 配置文件
		String resource = "SqlMapConfig.xml";
		InputStream inputStream  = Resources.getResourceAsStream(resource);
		// 使用SqlSessionFactoryBuilder从xml配置文件中创建SqlSessionFactory
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	/**
	 * // 根据 id查询用户信息
	 */
	@Test
	public void testFindUserById(){
		//数据库会话实例
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			// 查询单个记录，根据用户id查询用户信息
			User user = sqlSession.selectOne("test.findUserById",10);
			// 输出用户信息
			System.out.println(user.getId()+"---"+user.getUsername()+"---"+user.getBirthday()+"---"+user.getSex()+"---"+user.getAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}
	
	/**
	 * 根据用户名查询用户信息(模糊查询)
	 */
	@Test
	public void testFindUserByUsername(){
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			List<User> userList = sqlSession.selectList("test.findUserByUsername", "小");
			for (User user : userList) {
				System.out.println(user.getId()+"---"+user.getUsername()+"---"+user.getBirthday()+"---"+user.getSex()+"---"+user.getAddress());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}
	
	/**
	 * 添加用户
	 */
	@Test
	public void testInsert(){
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			User user = new User();
			// 添加用户信息
			user.setUsername("小刚");
			user.setSex("女");
			user.setBirthday(new Date());
			user.setAddress("顺义区");
			sqlSession.insert("test.insertUser",user);
			//提交事务
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}
	
	/**
	 * 删除用户
	 */
	@Test
	public void testDelete() {
		// 数据库会话实例
		SqlSession sqlSession = null;
		try {
			// 创建数据库会话实例sqlSession
			sqlSession = sqlSessionFactory.openSession();
			// 删除用户
			sqlSession.delete("test.deleteUserById",1);
			// 提交事务
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	/**
	 * 更新用户信息
	 */
	@Test
	public void testUpdate() {
		// 数据库会话实例
		SqlSession sqlSession = null;
		try {
			// 创建数据库会话实例sqlSession
			sqlSession = sqlSessionFactory.openSession();
			// 添加用户信息
			User user = new User();
			user.setId(0);
			user.setUsername("张小明");
			user.setAddress("河南郑州");
			user.setBirthday(new Date());
			user.setSex("男");
			sqlSession.update("test.updateUser", user);
			// 提交事务
			sqlSession.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

}
