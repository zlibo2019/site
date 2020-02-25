package com.weds.site.entity;

import org.apache.ibatis.annotations.Param;

public class DockingInsertSuccessLogEntity {
	String parentId;
	int sendLx;
	String regSerial;
	Integer ParentXh;
	String idCardNumber;
	String requestSerial;
	Integer userSerial;

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getSendLx() {
		return sendLx;
	}

	public void setSendLx(int sendLx) {
		this.sendLx = sendLx;
	}

	public String getRegSerial() {
		return regSerial;
	}

	public void setRegSerial(String regSerial) {
		this.regSerial = regSerial;
	}

	public Integer getParentXh() {
		return ParentXh;
	}

	public void setParentXh(Integer parentXh) {
		ParentXh = parentXh;
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

	public Integer getUserSerial() {
		return userSerial;
	}

	public void setUserSerial(Integer userSerial) {
		this.userSerial = userSerial;
	}

}
