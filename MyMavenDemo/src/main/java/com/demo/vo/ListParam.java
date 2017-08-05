package com.demo.vo;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.demo.pojo.Subject;

public class ListParam implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
//	private List<Subject> dts;
	private Subject[] dts;
	private Info info;
	private Car car;

	@NotNull(message="name不能为空")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@NotNull(message="dts不能为空")
//	@Size(max=3,min=1,message="dts数组最大3，最小1")
	public Subject[] getDts() {
		return dts;
	}

	public void setDts(Subject[] dts) {
		this.dts = dts;
	}

	@Valid
	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

//	public List<Subject> getDts() {
//		return dts;
//	}
//
//	public void setDts(List<Subject> dts) {
//		this.dts = dts;
//	}


}
