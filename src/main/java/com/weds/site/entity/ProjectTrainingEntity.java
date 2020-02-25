package com.weds.site.entity;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

import java.util.List;

public class ProjectTrainingEntity extends BaseEntity {
	// 平台为项目分配的接入编号（必填）
	@NotNullAndEmptyValid
	private String projectCode;

	// 培训日期。格式yyyy-MM-dd（必填）
	@NotNullAndEmptyValid
	private String trainingDate;

	// 培训时长。单位：小时（必填）
	// private Long trainingDuration;
	private double trainingDuration;

	// 培训名称（必填）
	@NotNullAndEmptyValid
	private String trainingName;

	// 培训类型代码。参考培训类型字典表（必填）
	@NotNullAndEmptyValid
	private String trainingTypeCode;

	// 培训人
	private String trainer;

	// 培训机构
	private String trainingOrg;

	// 培训地址
	private String trainingAddress;

	// 培训简述
	private String description;

	// 附件。JSON数组。附件总数不超过10个
	private List<AttachmentsEntity> attachments;

	// 参与培训的工人。JSON数组
	@ColumnProperties(aes = true)
	private List<WorkersEntity> workers;

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getTrainingDate() {
		return trainingDate;
	}

	public void setTrainingDate(String trainingDate) {
		this.trainingDate = trainingDate;
	}

	public double getTrainingDuration() {
		return trainingDuration;
	}

	public void setTrainingDuration(double trainingDuration) {
		this.trainingDuration = trainingDuration;
	}

	public String getTrainingName() {
		return trainingName;
	}

	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}

	public String getTrainingTypeCode() {
		return trainingTypeCode;
	}

	public void setTrainingTypeCode(String trainingTypeCode) {
		this.trainingTypeCode = trainingTypeCode;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	public String getTrainingOrg() {
		return trainingOrg;
	}

	public void setTrainingOrg(String trainingOrg) {
		this.trainingOrg = trainingOrg;
	}

	public String getTrainingAddress() {
		return trainingAddress;
	}

	public void setTrainingAddress(String trainingAddress) {
		this.trainingAddress = trainingAddress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<AttachmentsEntity> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<AttachmentsEntity> attachments) {
		this.attachments = attachments;
	}

	public List<WorkersEntity> getWorkers() {
		return workers;
	}

	public void setWorkers(List<WorkersEntity> workers) {
		this.workers = workers;
	}
}
