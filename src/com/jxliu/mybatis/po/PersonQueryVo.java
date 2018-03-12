package com.jxliu.mybatis.po;

import java.util.List;

public class PersonQueryVo {
	private Person person;
	private List<Integer> ids;
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
}
