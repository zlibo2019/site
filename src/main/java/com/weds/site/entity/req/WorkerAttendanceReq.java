package com.weds.site.entity.req;

import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;
import com.weds.site.base.CustomPageSearch;

public class WorkerAttendanceReq extends CustomPageSearch {
    // 平台为项目分配的接入编号（必填）
    @NotNullAndEmptyValid
    private String projectCode;

    // 考勤日期。格式yyyy-MM-dd（必填）
    @NotNullAndEmptyValid
    private String date;

    // 平台为班组分配的接入编号
    private Integer teamSysNo;

    // 证件类型。参考人员证件类型字典表
    private String idCardType;

    // 证件号码。AES
    @ColumnProperties(aes = true)
    private String idCardNumber;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(Integer teamSysNo) {
        this.teamSysNo = teamSysNo;
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
}
