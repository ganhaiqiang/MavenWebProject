package com.demo.job;

import java.io.Serializable;

public class WeekJob implements Serializable {
	private static final long serialVersionUID = 1L;

	public void doJob(){
		System.out.println("启动后延迟10s启动。。。");
	}
}
