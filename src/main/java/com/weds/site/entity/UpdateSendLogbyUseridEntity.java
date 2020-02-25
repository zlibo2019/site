package com.weds.site.entity;

import com.weds.framework.core.validation.NotNullAndEmptyValid;

public class UpdateSendLogbyUseridEntity {

	private Integer xh;

	// 上传类型
	private Integer sendLx;

	// 身份证号
	private String idCardNumber;

	// 班组名
	private String teamName;

	// 发送结果
	private Integer sendCode;

	// 查询请求序号
	private String requestSerial;

	// 返回错误信息
	private String sendMsg;

	// 类型 1-进0 退
	private Integer type;

	// 进退场时间
	private String entryTime;

	private Integer userSerial;

	private String regSerial;

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	public Integer getSendLx() {
		return sendLx;
	}

	public void setSendLx(Integer sendLx) {
		this.sendLx = sendLx;
	}

	public String getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
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

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public Integer getUserSerial() {
		return userSerial;
	}

	public void setUserSerial(Integer userSerial) {
		this.userSerial = userSerial;
	}

	public String getRegSerial() {
		return regSerial;
	}

	public void setRegSerial(String regSerial) {
		this.regSerial = regSerial;
	}

}
