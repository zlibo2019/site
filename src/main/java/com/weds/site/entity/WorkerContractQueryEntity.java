package com.weds.site.entity;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

import java.util.List;

public class WorkerContractQueryEntity extends BaseEntity {
	// 项目编码（必填）
	@NotNullAndEmptyValid
	private String projectCode;

	// 工人所属企业统一社会信用编码（必填）
	@NotNullAndEmptyValid
	private String corpCode;

	// 工人所属企业名称（必填）
	@NotNullAndEmptyValid
	private String corpName;

	// 证件类型。参考人员证件类型字典表（必填）
	@NotNullAndEmptyValid
	private String idCardType;

	// 证件号码。AES（必填）
	@ColumnProperties(aes = true)
	@NotNullAndEmptyValid
	private String idCardNumber;

	// 合同期限类型。参考合同期限类型字典表（必填）
	private Integer contractPeriodType;

	// 生效日期（必填）
	@NotNullAndEmptyValid
	private String startDate;

	// 失效日期（必填）
	@NotNullAndEmptyValid
	private String endDate;

	// 计量单位。参考计量单位类型字典表
	private Integer unit;

	// 计量单价。根据结算方式，对应的单价，单位：元
	// private Long unitPrice;
	private double unitPrice;

	// 合同文件。JSON数组
	@ColumnProperties(aes = true)
	private List<AttachmentsEntity> attachments;

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

	public String getIdCardType() {
		return idCardType;
	}

	public void setIdCardType(String idCardType) {
		this.idCardType = idCardType;
	}

	public String getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public Integer getContractPeriodType() {
		return contractPeriodType;
	}

	public void setContractPeriodType(Integer contractPeriodType) {
		this.contractPeriodType = contractPeriodType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public List<AttachmentsEntity> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<AttachmentsEntity> attachments) {
		this.attachments = attachments;
	}
}
