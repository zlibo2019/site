package com.weds.site.entity;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

import java.util.List;

//
public class DockingTeamEntity extends BaseEntity {

	// 日志表XH
	private Integer xh;

	// 项目编号
	private Integer bh;

	private String regSerial;

	// 动作：0新增、1修改
	private Integer sendFun;

	// 班组编号（必填）
	private Integer teamSysNo;

	// 项目编码（必填）
	@NotNullAndEmptyValid
	private String projectCode;

	// 班组所在企业统一社会信用代码（必填）
	@NotNullAndEmptyValid
	private String corpCode;

	// 班组所在企业名称（必填）
	@NotNullAndEmptyValid
	private String corpName;

	// 班组名称（必填）
	@NotNullAndEmptyValid
	private String teamName;

	// 班组长姓名
	private String teamLeaderName;

	// 班组长联系方式
	private String teamLeaderPhone;

	// 班组长证件类型。参考人员证件类型字典表
	private String teamLeaderIDCardType;

	// 班组长证件号码。AES
	@ColumnProperties(aes = true)
	private String teamLeaderIDNumber;

	// 责任人姓名，班组所在企业负责人
	private String responsiblePersonName;

	// 责任人联系电话
	private String responsiblePersonPhone;

	// 责任人证件类型。参考人员证件类型字典表
	private String responsiblePersonIDCardType;

	// 责任人证件号码。AES
	@ColumnProperties(aes = true)
	private String responsiblePersonIDNumber;

	// 备注
	private String remark;

	// 进场日期 yyyy-MM-dd HH:mm:ss
	private String entryTime;

	// 退场日期yyyy-MM-dd HH:mm:ss
	private String exitTime;

	// 进场附件。JSON数组对象
	@ColumnProperties(aes = true)
	private List<AttachmentsEntity> entryAttachments;

	// 退场附件。JSON数组对象
	@ColumnProperties(aes = true)
	private List<AttachmentsEntity> exitAttachments;

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	public Integer getBh() {
		return bh;
	}

	public void setBh(Integer bh) {
		this.bh = bh;
	}

	public String getRegSerial() {
		return regSerial;
	}

	public void setRegSerial(String regSerial) {
		this.regSerial = regSerial;
	}

	public Integer getSendFun() {
		return sendFun;
	}

	public void setSendFun(Integer sendFun) {
		this.sendFun = sendFun;
	}

	public Integer getTeamSysNo() {
		return teamSysNo;
	}

	public void setTeamSysNo(Integer teamSysNo) {
		this.teamSysNo = teamSysNo;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getCorpCode() {
		return corpCode;
	}

	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamLeaderName() {
		return teamLeaderName;
	}

	public void setTeamLeaderName(String teamLeaderName) {
		this.teamLeaderName = teamLeaderName;
	}

	public String getTeamLeaderPhone() {
		return teamLeaderPhone;
	}

	public void setTeamLeaderPhone(String teamLeaderPhone) {
		this.teamLeaderPhone = teamLeaderPhone;
	}

	public String getTeamLeaderIDCardType() {
		return teamLeaderIDCardType;
	}

	public void setTeamLeaderIDCardType(String teamLeaderIDCardType) {
		this.teamLeaderIDCardType = teamLeaderIDCardType;
	}

	public String getTeamLeaderIDNumber() {
		return teamLeaderIDNumber;
	}

	public void setTeamLeaderIDNumber(String teamLeaderIDNumber) {
		this.teamLeaderIDNumber = teamLeaderIDNumber;
	}

	public String getResponsiblePersonName() {
		return responsiblePersonName;
	}

	public void setResponsiblePersonName(String responsiblePersonName) {
		this.responsiblePersonName = responsiblePersonName;
	}

	public String getResponsiblePersonPhone() {
		return responsiblePersonPhone;
	}

	public void setResponsiblePersonPhone(String responsiblePersonPhone) {
		this.responsiblePersonPhone = responsiblePersonPhone;
	}

	public String getResponsiblePersonIDCardType() {
		return responsiblePersonIDCardType;
	}

	public void setResponsiblePersonIDCardType(String responsiblePersonIDCardType) {
		this.responsiblePersonIDCardType = responsiblePersonIDCardType;
	}

	public String getResponsiblePersonIDNumber() {
		return responsiblePersonIDNumber;
	}

	public void setResponsiblePersonIDNumber(String responsiblePersonIDNumber) {
		this.responsiblePersonIDNumber = responsiblePersonIDNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public List<AttachmentsEntity> getEntryAttachments() {
		return entryAttachments;
	}

	public void setEntryAttachments(List<AttachmentsEntity> entryAttachments) {
		this.entryAttachments = entryAttachments;
	}

	public List<AttachmentsEntity> getExitAttachments() {
		return exitAttachments;
	}

	public void setExitAttachments(List<AttachmentsEntity> exitAttachments) {
		this.exitAttachments = exitAttachments;
	}
}