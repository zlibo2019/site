package com.weds.site.entity;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

import java.util.List;

public class WorkerEntryExitAddEntity extends BaseEntity {
    // 项目编码（必填）
    @NotNullAndEmptyValid
    private String projectCode;

    // 工人所属企业统一社会信用编码（必填）
    @NotNullAndEmptyValid
    private String corpCode;

    // 工人所属企业名称（必填）
    @NotNullAndEmptyValid
    private String corpName;

    // 班组编号（必填）
    private Integer teamSysNo;

    // 人员列表数据,JSON数组，数量不能超过50（必填）
    @ColumnProperties(aes = true)
    private List<WorkerListEntryEntity> workerList;

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

    public List<WorkerListEntryEntity> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(List<WorkerListEntryEntity> workerList) {
        this.workerList = workerList;
    }
}
