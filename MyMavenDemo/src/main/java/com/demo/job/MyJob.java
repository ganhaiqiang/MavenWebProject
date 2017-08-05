package com.demo.job;

import java.io.Serializable;

public class MyJob implements Serializable {
	private static final long serialVersionUID = 1L;

	public void print() {
		System.out.println("定时任务执行。。。");
	}

	public void show() {
		// System.out.println("show==============");
	}
}
