package com.demo.pojo;

import java.io.Serializable;

/**
 * CreateOrderParamVo中的附加参数additionData
 * 
 * @author 80001267
 * 
 */
public class PayPeriodDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private int itemIdx;// 行项目编号 ，付款ID的行项目编号(明细，从1开始编号) 1，2，3…
	private String subCustCode;// 月结账号
	private String caType = "01";// 合同账户类型，01-月结，02-COD货款，03-COD服务费，04-垫付本金，05-垫付服务费
	private Double amt;// 金额，各种业务实际抵扣的金额，（单位：分）
	private String payPeriod;// 账期，各种业务实际抵扣应付、应收的账期
	private String prctr;// 网点，清帐网点 (退款用)

	public int getItemIdx() {
		return itemIdx;
	}

	public void setItemIdx(int itemIdx) {
		this.itemIdx = itemIdx;
	}

	public String getSubCustCode() {
		return subCustCode;
	}

	public void setSubCustCode(String subCustCode) {
		this.subCustCode = subCustCode;
	}

	public String getCaType() {
		return caType;
	}

	public void setCaType(String caType) {
		this.caType = caType;
	}

	public Double getAmt() {
		return amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}

	public String getPayPeriod() {
		return payPeriod;
	}

	public void setPayPeriod(String payPeriod) {
		this.payPeriod = payPeriod;
	}

	public String getPrctr() {
		return prctr;
	}

	public void setPrctr(String prctr) {
		this.prctr = prctr;
	}
}