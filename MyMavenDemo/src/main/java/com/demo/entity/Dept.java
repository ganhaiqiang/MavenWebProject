/*
 * Dept.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-06 Created
 */
package com.demo.entity;

/**
 * 部门表
 * 
 * @author administrator
 * @version 1.0 2017-08-06
 */
public class Dept {

	/**
	 * 表示部门编号，由两位数字所组成
	 */
	private Long deptno;
	/**
	 * 部门名称，最多由14个字符所组成
	 */
	private String dname;
	/**
	 * 部门所在的位置
	 */
	private String loc;

	public Long getDeptno() {
		return deptno;
	}

	public void setDeptno(Long deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname == null ? null : dname.trim();
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc == null ? null : loc.trim();
	}
}