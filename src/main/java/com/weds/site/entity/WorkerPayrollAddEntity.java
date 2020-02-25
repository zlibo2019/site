package com.weds.site.entity;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

import java.util.List;

public class WorkerPayrollAddEntity extends BaseEntity {
    // 平台为项目分配的接入编码（必填）
    @NotNullAndEmptyValid
    private String projectCode;

    // 工人所属企业统一社会信用代码，如果无统一社会信用代码，则填写组织机构代码（必填）
    @NotNullAndEmptyValid
    private String corpCode;

    // 工人所属企业名称（必填）
    @NotNullAndEmptyValid
    private String corpName;

    // 平台为班组分配的接入编号（必填）
    private Integer teamSysNo;

    // 发放工资的月份。格式yyyy-MM（必填）
    @NotNullAndEmptyValid
    private String payMonth;

    // 工资单附件。JSON数组，附件总数不超过5个。
    private List<AttachmentsEntity> attachments;

    // 工资单详情列表。JSON数组，总数不超过20条。（必填）
    @ColumnProperties(aes = true)
    private List<DetailListEntity> detailList;

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

    public Integer getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(Integer teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth;
    }

    public List<AttachmentsEntity> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentsEntity> attachments) {
        this.attachments = attachments;
    }

    public List<DetailListEntity> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<DetailListEntity> detailList) {
        this.detailList = detailList;
    }
}
