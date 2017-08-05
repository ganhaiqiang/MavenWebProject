package com.demo.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Info implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String custCode;
	private String deptCode;

	@NotNull(message="custCode不能为空")
	@Pattern(regexp="ABC|QWE",message="custCode类型校验失败")
	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	@NotNull(message="deptCode不能为空")
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

}
