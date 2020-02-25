package com.weds.site.entity;

import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

public class HaiyangWorkerListEntity {
	
	
	
	// 工人序號（必填）
	@NotNullAndEmptyValid
	private String userSerial;
	
	
	// 工人姓名（必填）
	@NotNullAndEmptyValid
	private String migName;
	
	// 身份证号码（必填）
	@NotNullAndEmptyValid
	private String idCard;
	
	// 档案照片（必填）不超过50KB的Base64字符串（必填）
	@NotNullAndEmptyValid
	@ColumnProperties(image = true)
	private String image; 

	// 家庭住址照片（必填）
	@NotNullAndEmptyValid
	private String homeAddress; 

	// 民族（必填）
	@NotNullAndEmptyValid
	private String nation; 
	
	// 电话（必填）
	@NotNullAndEmptyValid
	private String tel; 


	// 是否班组长（必填）
	@NotNullAndEmptyValid
	private String isTeamLeader;
	
	// 工种（必填）
	@NotNullAndEmptyValid
	private String workType;

	// 文化程度（必填）
	@NotNullAndEmptyValid
	private String educLevel;
	
	// 政治面貌（必填）
	@NotNullAndEmptyValid
	private String pltType;

	// 籍贯（必填）
	@NotNullAndEmptyValid
	private String nativePlace;

	// 发证机关（必填）
	@NotNullAndEmptyValid
	private String grantorg;
	
	// 银行卡号（必填）
	@NotNullAndEmptyValid
	private String acctNum;
	
	// 班组（必填）
	@NotNullAndEmptyValid
	private String workTeam;
	
	// 工人类型（必填）
	@NotNullAndEmptyValid
	private String workRole;
	
	// 组织结构代码（必填）
	@NotNullAndEmptyValid
	private String corpId;
	
	// 所属企业名称（必填）
	@NotNullAndEmptyValid
	private String ownCorp;
	
	// 工作状态是否离职（必填）
	@NotNullAndEmptyValid
	private String status;
	
	// 是否签订劳动合同（必填）
	@NotNullAndEmptyValid
	private String contractLabor;
	
	// 入场时间（必填）
	@NotNullAndEmptyValid
	private String entryTime;
	
	// 退场时间（必填）
	@NotNullAndEmptyValid
	private String exitTime;

	public String getMigName() {
		return migName;
	}

	public void setMigName(String migName) {
		this.migName = migName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getWorkType() {
		return workType;
	}



	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getGrantorg() {
		return grantorg;
	}

	public void setGrantorg(String grantorg) {
		this.grantorg = grantorg;
	}

	public String getAcctNum() {
		return acctNum;
	}

	public void setAcctNum(String acctNum) {
		this.acctNum = acctNum;
	}

	public String getWorkTeam() {
		return workTeam;
	}

	public void setWorkTeam(String workTeam) {
		this.workTeam = workTeam;
	}


	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getOwnCorp() {
		return ownCorp;
	}

	public void setOwnCorp(String ownCorp) {
		this.ownCorp = ownCorp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContractLabor() {
		return contractLabor;
	}

	public void setContractLabor(String contractLabor) {
		this.contractLabor = contractLabor;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getExitTime() {
		return exitTime;
	}

	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}

	public String getEducLevel() {
		return educLevel;
	}

	public void setEducLevel(String educLevel) {
		this.educLevel = educLevel;
	}

	public String getPltType() {
		return pltType;
	}

	public void setPltType(String pltType) {
		this.pltType = pltType;
	}

	public String getWorkRole() {
		return workRole;
	}

	public void setWorkRole(String workRole) {
		this.workRole = workRole;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getIsTeamLeader() {
		return isTeamLeader;
	}

	public void setIsTeamLeader(String isTeamLeader) {
		this.isTeamLeader = isTeamLeader;
	}

	public String getUserSerial() {
		return userSerial;
	}

	public void setUserSerial(String userSerial) {
		this.userSerial = userSerial;
	}
	
	
	
	
}
