package com.weds.site.entity;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

import java.util.List;

//
public class ProjectTeamAddEntity extends BaseEntity {
    // 项目编码（必填）
    @NotNullAndEmptyValid
    private String projectCode;

    // 班组所在企业统一社会信用代码（必填）
    @NotNullAndEmptyValid
    private String corpCode;

    // 班组所在企业名称（必填）
    @NotNullAndEmptyValid
    private String corpName;

    // 班组名称，同一个项目下面不能重复（必填）
    @NotNullAndEmptyValid
    private String teamName;

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

    // 进场日期，yyyy-MM-dd
    private String entryTime;

    // 退场日期，yyyy-MM-dd
    private String exitTime;

    // 进场附件，有进场日期时，此字段必填。JSON数组。附件总数不超过5个
    @ColumnProperties(aes = true)
    private List<AttachmentsEntity> entryAttachments;

    // 退场附件，有退场日期时，此字段必填。JSON数组。附件总数不超过5个
    @ColumnProperties(aes = true)
    private List<AttachmentsEntity> exitAttachments;

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