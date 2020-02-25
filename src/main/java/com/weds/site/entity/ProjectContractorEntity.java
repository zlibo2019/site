package com.weds.site.entity;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

import java.util.List;

public class ProjectContractorEntity extends BaseEntity {
	// 项目编码（必填）
	@NotNullAndEmptyValid
	private String projectCode;

	// 统一社会信用代码，如果无统一社会信用代码，则填写组织机构代码（必填）
	@NotNullAndEmptyValid
	private String corpCode;

	// 企业名称（必填）
	@NotNullAndEmptyValid
	private String corpName;

	// 参建类型。参考参建单位类型字典表（必填）
	@NotNullAndEmptyValid
	private String corpType;

	// 进场时间。格式yyyy-MM-dd HH:mm:ss
	private String entryTime;

	// 退场时间。格式yyyy-MM-dd HH:mm:ss
	private String exitTime;

	// 发放工资的银行。JSON数组
	@ColumnProperties(aes = true)
	private List<BankInfosEntity> bankInfos;

	// 项目经理名称
	private String pmName;

	// 项目经理证件类型。参考人员证件类型字典表
	private String pmIDCardType;

	// 项目经理证件号码。AES
	@ColumnProperties(aes = true)
	private String pmIDCardNumber;

	// 项目经理电话
	private String pmPhone;

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

	public String getCorpType() {
		return corpType;
	}

	public void setCorpType(String corpType) {
		this.corpType = corpType;
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

	public List<BankInfosEntity> getBankInfos() {
		return bankInfos;
	}

	public void setBankInfos(List<BankInfosEntity> bankInfos) {
		this.bankInfos = bankInfos;
	}

	public String getPmName() {
		return pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
	}

	public String getPmIDCardType() {
		return pmIDCardType;
	}

	public void setPmIDCardType(String pmIDCardType) {
		this.pmIDCardType = pmIDCardType;
	}

	public String getPmIDCardNumber() {
		return pmIDCardNumber;
	}

	public void setPmIDCardNumber(String pmIDCardNumber) {
		this.pmIDCardNumber = pmIDCardNumber;
	}

	public String getPmPhone() {
		return pmPhone;
	}

	public void setPmPhone(String pmPhone) {
		this.pmPhone = pmPhone;
	}
}
