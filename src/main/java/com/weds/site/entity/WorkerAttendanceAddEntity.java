package com.weds.site.entity;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

import java.util.List;

public class WorkerAttendanceAddEntity extends BaseEntity {
    // 平台为项目分配的接入编号（必填）
    @NotNullAndEmptyValid
    private String projectCode;

    // 平台生成的班组编号（必填）
    private Integer teamSysNo;

    // 考勤列表。JSON数组，数组长度不超过20（必填）
    @ColumnProperties(aes = true)
    private List<DataListEntity> dataList;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Integer getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(Integer teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

    public List<DataListEntity> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListEntity> dataList) {
        this.dataList = dataList;
    }
}
