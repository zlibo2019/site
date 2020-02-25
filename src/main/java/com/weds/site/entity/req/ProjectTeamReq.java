package com.weds.site.entity.req;

import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.base.CustomPageSearch;


public class ProjectTeamReq extends CustomPageSearch {
    // 班组编号
    private Integer teamSysNo;

    // 项目编码（必填）
    @NotNullAndEmptyValid
    private String projectCode;

    // 班组名称
    private String teamName;

    // 班组所在企业统一社会信用代码
    private String corpCode;

    // 班组所在企业名称
    private String corpName;

    public Integer getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(Integer teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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
}