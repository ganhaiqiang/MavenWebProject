package com.demo.pojo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "id不能为空！")
	@Min(value = 100)
	private int id;

	@NotBlank(message = "{name_not_null}")
	private String name;

	@NotNull
	private String sex;

	@Max(value = 100)
	@Min(value = 1)
	private Double age;

	@NotNull
	private String phone;

	@NotNull
	private String address;

	@NotBlank
	private String picture;

	private Picture pic;

	private List<Subject> subjects;

	public Student(int id, String name, String sex, double age, String phone, String address, String picture) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.phone = phone;
		this.address = address;
		this.picture = picture;
	}

	public Student() {
		super();
	}

	public Picture getPic() {
		return pic;
	}

	public void setPic(Picture pic) {
		this.pic = pic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Double getAge() {
		return age;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

}
