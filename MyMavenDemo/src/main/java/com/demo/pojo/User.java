package com.demo.pojo;

import java.util.Date;

public class User {
	private Integer id;
	private String name;
	private Date birthday;
	private boolean flag;

	public User(Integer id, String name, Date birthday, boolean flag) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.flag = flag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
