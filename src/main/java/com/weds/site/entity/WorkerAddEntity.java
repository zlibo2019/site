package com.weds.site.entity;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

import java.util.List;

public class WorkerAddEntity extends BaseEntity {
    // 平台为项目分配的接入编号（必填）
    @NotNullAndEmptyValid
    private String projectCode;

    // 所在企业统一社会信用代码（必填）
    @NotNullAndEmptyValid
    private String corpCode;

    // 所在企业名称（必填）
    @NotNullAndEmptyValid
    private String corpName;

    // 班组编号（必填）
    private Integer teamSysNo;

    // 班组名称（必填）
    @NotNullAndEmptyValid
    private String teamName;

    // 人员列表数据,JSON数组，数量不能超过5（必填）
    @ColumnProperties(aes = true)
    private List<WorkerListEntity> workerList;

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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<WorkerListEntity> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(List<WorkerListEntity> workerList) {
        this.workerList = workerList;
    }
}
