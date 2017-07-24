/*
 * SalGrade.java
 * Copyright(C) 20xx-2015 xxxxxx��˾
 * All rights reserved.
 * -----------------------------------------------
 * 2017-07-24 Created
 */
package com.project.entity;

/**
 * ���ʵȼ���
 * 
 * @author administrator
 * @version 1.0 2017-07-24
 */
public class SalGrade {

    /**
     * ���ʵĵȼ�
     */
    private Integer grade;
    /**
     * �˵ȼ�����͹���
     */
    private Integer losal;
    /**
     * �˵ȼ�����߹���
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