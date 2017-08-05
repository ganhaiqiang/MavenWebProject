package com.demo.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 扫码支付接口输入参数
 * 
 * @author 80001267
 * 
 */
public class CreateOrderParamVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**************************** 系统参数 ******************************/
	private String serviceName = "CREATE_ORDER_PAY"; // 接口名称
	private String serviceVersion = "V1.0.0"; // 接口版本
	private String charset = "UTF-8"; // 字符编码
	private String signType = "RSA"; // 签名方式
	private Date requestTime; // 请求时间，格式：yyyyMMddhhmmssSSS
	private String sign; // RSA签名

	/**************************** 业务参数 ******************************/
	private String mchNo;// 平台商户代码，合作平台商户接入顺银时由顺银预先分配的唯一识别编号
	private String outOrderNo;// 商户订单号，巴枪不用传，其他必填
	private Double amount;// 请求支付的订单金额，单位：分， 格式：1638表示16.38元
	private String ccy = "RMB";// 币种
	private String channelType = "ALIPAY";// 渠道类型，顺手付：SFPAY 微信：WXPAY 支付宝：ALIPAY
	private String orderType = "YJ";// SD(散单)/COD/YJ(月结)
	private String sourceCode = "SST";// 来源渠道
	private String operatorNo;// 操作员编号(收派员工号)，对于会员系统，可以为空
	private String orgId;// 机构ID
	private String termCode;// 终端号，对于会员系统，可以为空
	private String externalSn;// 外部流水号（交易参考号）
	private String requestIp;// 请求IP
	private String detailList;// 消费运单明细，SST无需填写
	private AdditionInfo additionInfo;// 附加参数
	private List<PayPeriodDetail> additionData;// 附加参数

	public String getMchNo() {
		return mchNo;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceVersion() {
		return serviceVersion;
	}

	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public void setMchNo(String mchNo) {
		this.mchNo = mchNo;
	}

	public String getOutOrderNo() {
		return outOrderNo;
	}

	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getOperatorNo() {
		return operatorNo;
	}

	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getTermCode() {
		return termCode;
	}

	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}

	public String getExternalSn() {
		return externalSn;
	}

	public void setExternalSn(String externalSn) {
		this.externalSn = externalSn;
	}

	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	public String getDetailList() {
		return detailList;
	}

	public void setDetailList(String detailList) {
		this.detailList = detailList;
	}

	public AdditionInfo getAdditionInfo() {
		return additionInfo;
	}

	public void setAdditionInfo(AdditionInfo additionInfo) {
		this.additionInfo = additionInfo;
	}

	public List<PayPeriodDetail> getAdditionData() {
		return additionData;
	}

	public void setAdditionData(List<PayPeriodDetail> additionData) {
		this.additionData = additionData;
	}
}
