package mybatis_demo;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.jxliu.mybatis.mapper.UserMapper;
import com.jxliu.mybatis.po.QueryVo;
import com.jxliu.mybatis.po.User;

public class UserMapperTest {
	
	private SqlSessionFactory sqlSessionFactory = null;

	@Before
	public void init() throws Exception {
		// 第一步：创建一个SQLSessionFactoryBuilder对象。
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
		// 第二步：加载配置文件。
		InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		// 第三步：创建SQLSessionFactory对象
		sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
	}

	@Test
	public void testGetUserById() {
		//和spring整合后省略
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//获得代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user;
		try {
			user = userMapper.getUserById(5);
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//和spring整合后省略
		sqlSession.close();
	}

	@Test
	public void testGetUserByName() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> list;
		try {
			list = userMapper.getUserByName("小");
			for (User user : list) {
				System.out.println(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sqlSession.close();
	}

	@Test
	public void testInsertUser() {
		fail("Not yet implemented");
	}
	
	/**
	 * 测试通过queryVo查询数据
	 */
	@Test
	public void testGetUserList(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		QueryVo queryVo = new QueryVo();
		User user = new User();
		user.setUsername("小");
		queryVo.setUser(user);
		try {
			List<User> userList = userMapper.getUserListByUsername(queryVo);
			for (User user2 : userList) {
				System.out.println(user2.getId()+"===="+user2.getUsername()+"===="+user2.getBirthday()+"===="+user2.getAddress()+"===="+user2.getSex());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
	}
	
	/**
	 * 查询列表总数
	 */
	@Test
	public void testGetUserCount(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		try {
			int userCount = userMapper.getUserCount();
			System.out.println(userCount);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
	}

	
	
}
