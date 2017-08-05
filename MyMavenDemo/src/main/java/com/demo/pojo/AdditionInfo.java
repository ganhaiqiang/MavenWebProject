package com.demo.pojo;

import java.io.Serializable;

/**
 * CreateOrderParamVo中的附加参数additionInfo
 * 
 * @author 80001267
 *
 */
public class AdditionInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String custCode;// 结算账号（BP），必填
	private String burks;// 公司代码
	private String deptCode;// 网点，必填

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getBurks() {
		return burks;
	}

	public void setBurks(String burks) {
		this.burks = burks;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
}
