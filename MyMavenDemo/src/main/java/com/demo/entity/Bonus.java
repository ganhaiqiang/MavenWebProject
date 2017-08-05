/*
 * Bonus.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-06 Created
 */
package com.demo.entity;

/**
 * 工资表
 * 
 * @author administrator
 * @version 1.0 2017-08-06
 */
public class Bonus {

	/**
	 * 雇员姓名
	 */
	private String ename;
	/**
	 * 雇员职位
	 */
	private String job;
	/**
	 * 雇员的工资
	 */
	private Integer sal;
	/**
	 * 雇员的奖金
	 */
	private Integer comm;

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename == null ? null : ename.trim();
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job == null ? null : job.trim();
	}

	public Integer getSal() {
		return sal;
	}

	public void setSal(Integer sal) {
		this.sal = sal;
	}

	public Integer getComm() {
		return comm;
	}

	public void setComm(Integer comm) {
		this.comm = comm;
	}
}