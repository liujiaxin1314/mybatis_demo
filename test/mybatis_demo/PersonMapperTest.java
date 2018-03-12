package mybatis_demo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.jxliu.mybatis.mapper.PersonMapper;
import com.jxliu.mybatis.po.Person;
import com.jxliu.mybatis.po.PersonQueryVo;

public class PersonMapperTest {
	
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
	public void testGetPersonList(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
		try {
			List<Integer> ids = new ArrayList<Integer>();
			ids.add(1);
			ids.add(3);
			PersonQueryVo personQueryVo = new PersonQueryVo();
			Person person = new Person();
			person.setName("明");
			personQueryVo.setPerson(person);
			personQueryVo.setIds(ids);
			List<Person> personList = personMapper.getPersonList(personQueryVo);
			for (Person person2 : personList) {
				System.out.println(person2.getId()+"#$%^&"+person2.getName()+"#$%^&"+person2.getAddress()+"#$%^&"+person2.getBirthday());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
	}
	
}
