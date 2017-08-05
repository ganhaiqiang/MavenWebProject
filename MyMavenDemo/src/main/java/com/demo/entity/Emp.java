/*
 * Emp.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-06 Created
 */
package com.demo.entity;

import java.util.Date;

/**
 * 员工表
 * 
 * @author administrator
 * @version 1.0 2017-08-06
 */
public class Emp {

	/**
	 * 雇员的编号，由四位数字所组成
	 */
	private Integer empno;
	/**
	 * 雇员的姓名，由10位字符所组成
	 */
	private String ename;
	/**
	 * 中文姓名
	 */
	private String cname;
	/**
	 * 工作邮箱
	 */
	private String email;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 雇员的职位
	 */
	private String job;
	/**
	 * 雇员对应的领导编号，领导也是雇员
	 */
	private Long mgr;
	/**
	 * 雇员的雇佣日期
	 */
	private Date hiredate;
	/**
	 * 基本工资，其中有两位小数，五倍整数，一共是七位
	 */
	private Long sal;
	/**
	 * 奖金，佣金
	 */
	private Long comm;
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * 雇员所在的部门编号
	 */
	private Short deptno;

	public Integer getEmpno() {
		return empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename == null ? null : ename.trim();
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname == null ? null : cname.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job == null ? null : job.trim();
	}

	public Long getMgr() {
		return mgr;
	}

	public void setMgr(Long mgr) {
		this.mgr = mgr;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public Long getSal() {
		return sal;
	}

	public void setSal(Long sal) {
		this.sal = sal;
	}

	public Long getComm() {
		return comm;
	}

	public void setComm(Long comm) {
		this.comm = comm;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Short getDeptno() {
		return deptno;
	}

	public void setDeptno(Short deptno) {
		this.deptno = deptno;
	}
}