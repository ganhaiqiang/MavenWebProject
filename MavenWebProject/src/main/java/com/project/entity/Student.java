package com.project.entity;

import java.util.Date;

/**
 * 学生信息表
 * 
 * @author administrator
 * @version 1.0 2017-07-09
 */
public class Student {

	/**
	 * 学生ID，自增
	 */
	private Long id;
	/**
	 * 学生姓名
	 */
	private String name;
	/**
	 * 学生性别
	 */
	private String sex;
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 联系地址
	 */
	private String address;
	/**
	 * 联系邮箱
	 */
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}
}