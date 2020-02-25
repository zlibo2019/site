package com.weds.site.entity.req;

import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.base.CustomPageSearch;


public class ProjectContractorReq extends CustomPageSearch {
    // 项目编码（必填）
    @NotNullAndEmptyValid
    private String projectCode;

    // 统一社会代码,如果无统一社会用代码，则用组织机构代码
    private String corpCode;

    // 企业名称
    private String corpName;

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
}