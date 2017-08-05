package com.demo.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.demo.pojo.Student;

public class StudentList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date create;

	@NotNull
	@Valid
	private List<Student> stuList;

	public List<Student> getStuList() {
		return stuList;
	}

	public void setStuList(List<Student> stuList) {
		this.stuList = stuList;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

}
