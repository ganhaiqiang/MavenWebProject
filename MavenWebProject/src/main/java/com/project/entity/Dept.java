/*
 * Dept.java
 * Copyright(C) 20xx-2015 xxxxxx��˾
 * All rights reserved.
 * -----------------------------------------------
 * 2017-07-24 Created
 */
package com.project.entity;

/**
 * ���ű�
 * 
 * @author administrator
 * @version 1.0 2017-07-24
 */
public class Dept {

    /**
     * ��ʾ���ű�ţ�����λ���������
     */
    private Long deptno;
    /**
     * �������ƣ������14���ַ������
     */
    private String dname;
    /**
     * �������ڵ�λ��
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