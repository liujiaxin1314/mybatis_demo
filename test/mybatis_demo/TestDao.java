package mybatis_demo;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.jxliu.mybatis.dao.UserDao;
import com.jxliu.mybatis.dao.UserDaoImpl;
import com.jxliu.mybatis.po.User;

public class TestDao {
private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void init() throws Exception {
		SqlSessionFactoryBuilder sessionFactoryBuilder = new SqlSessionFactoryBuilder();
		InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		sqlSessionFactory = sessionFactoryBuilder.build(inputStream);
	}

	@Test
	public void testGetUserById() {
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		User user;
		try {
			user = userDao.findUserById(0);
			System.out.println(user.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

