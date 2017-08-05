/*
 * SalGrade.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-06 Created
 */
package com.demo.entity;

/**
 * 工资等级表
 * 
 * @author administrator
 * @version 1.0 2017-08-06
 */
public class SalGrade {

	/**
	 * 工资的等级
	 */
	private Integer grade;
	/**
	 * 此等级的最低工资
	 */
	private Integer losal;
	/**
	 * 此等级的最高工资
	 */
	private Integer hisal;

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getLosal() {
		return losal;
	}

	public void setLosal(Integer losal) {
		this.losal = losal;
	}

	public Integer getHisal() {
		return hisal;
	}

	public void setHisal(Integer hisal) {
		this.hisal = hisal;
	}
}