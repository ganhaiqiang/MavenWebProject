package com.demo.pojo;

import java.io.Serializable;

public class Picture implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String studentId;
	private String picture;
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
}
