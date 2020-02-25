package com.weds.site.entity;

import com.weds.framework.core.validation.NotNullAndEmptyValid;

public class UpdateSendLogEntity {

	private Integer xh;

	// 发送结果
	private Integer sendCode;

	// 查询请求序号
	private String requestSerial;

	// 返回错误信息
	private String sendMsg;

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	public String getRequestSerial() {
		return requestSerial;
	}

	public void setRequestSerial(String requestSerial) {
		this.requestSerial = requestSerial;
	}

	public String getSendMsg() {
		return sendMsg;
	}

	public void setSendMsg(String sendMsg) {
		this.sendMsg = sendMsg;
	}

	public Integer getSendCode() {
		return sendCode;
	}

	public void setSendCode(Integer sendCode) {
		this.sendCode = sendCode;
	}

}
