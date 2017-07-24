/*
 * Bonus.java
 * Copyright(C) 20xx-2015 xxxxxx��˾
 * All rights reserved.
 * -----------------------------------------------
 * 2017-07-24 Created
 */
package com.project.entity;

/**
 * ���ʱ�
 * 
 * @author administrator
 * @version 1.0 2017-07-24
 */
public class Bonus {

    /**
     * ��Ա����
     */
    private String ename;
    /**
     * ��Աְλ
     */
    private String job;
    /**
     * ��Ա�Ĺ���
     */
    private Integer sal;
    /**
     * ��Ա�Ľ���
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