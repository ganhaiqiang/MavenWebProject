package com.demo.vo;

import java.io.Serializable;

/**
 * 接口返回通用封装类
 * 
 * @author Administrator
 * @param <T>
 *
 */
public class Result<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean success; // 是否成功
	private String errorCode; // 错误代码
	private String errorMessage; // 描述信息，若为失败，则用于错误描述信息
	private T data; // 返回数据

	public Result() {
		super();
	}

	public Result(boolean success) {
		super();
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
