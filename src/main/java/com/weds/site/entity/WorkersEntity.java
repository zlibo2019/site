package com.weds.site.entity;

import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

public class WorkersEntity {
	// 培训详情表ID
	private Integer id;

	// 证件类型。参考人员证件类型字典表（必填）
	@NotNullAndEmptyValid
	private String idCardType;

	// 证件号码。AES（必填）
	@NotNullAndEmptyValid
	@ColumnProperties(aes = true)
	private String idCardNumber;

	// 是否合格。参考是否字典表（必填）
	private Integer isPass;

	// 培训得分,分值0~100，可以保留1位小数
	private Long trainingScore;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getIsPass() {
		return isPass;
	}

	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}

	public Long getTrainingScore() {
		return trainingScore;
	}

	public void setTrainingScore(Long trainingScore) {
		this.trainingScore = trainingScore;
	}
}
