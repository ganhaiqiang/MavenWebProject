/*
 * Emp.java
 * Copyright(C) 20xx-2015 xxxxxx��˾
 * All rights reserved.
 * -----------------------------------------------
 * 2017-07-24 Created
 */
package com.project.entity;

import java.util.Date;

/**
 * emp
 * 
 * @author administrator
 * @version 1.0 2017-07-24
 */
public class Emp {

    /**
     * ��Ա�ı�ţ�����λ���������
     */
    private Integer empno;
    /**
     * ��Ա����������10λ�ַ������
     */
    private String ename;
    /**
     * ��Ա��ְλ
     */
    private String job;
    /**
     * ��Ա��Ӧ���쵼��ţ��쵼Ҳ�ǹ�Ա
     */
    private Long mgr;
    /**
     * ��Ա�Ĺ�Ӷ����
     */
    private Date hiredate;
    /**
     * �������ʣ���������λС�����屶������һ������λ
     */
    private Long sal;
    /**
     * ����Ӷ��
     */
    private Long comm;
    /**
     * ��Ա���ڵĲ��ű��
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
    public Short getDeptno() {
        return deptno;
    }
    public void setDeptno(Short deptno) {
        this.deptno = deptno;
    }
}