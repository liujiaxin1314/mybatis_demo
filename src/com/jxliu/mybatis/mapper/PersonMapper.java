package com.jxliu.mybatis.mapper;

import java.util.List;

import com.jxliu.mybatis.po.Person;
import com.jxliu.mybatis.po.PersonQueryVo;

public interface PersonMapper {
	public List<Person> getPersonList(PersonQueryVo personQueryVo);
}
