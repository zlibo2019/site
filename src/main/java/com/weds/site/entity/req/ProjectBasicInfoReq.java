package com.weds.site.entity.req;

import com.weds.site.base.CustomPageSearch;

public class ProjectBasicInfoReq extends CustomPageSearch {
    // 项目编码
    private String projectCode;

    // 总包统一社会代码,如果无统一社会信用代码，则用组织机构代码，与企业名称必传一个
    private String contractorCorpCode;

    // 总包企业名称，与统一社会代码必传一个
    private String contractorCorpName;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getContractorCorpCode() {
        return contractorCorpCode;
    }

    public void setContractorCorpCode(String contractorCorpCode) {
        this.contractorCorpCode = contractorCorpCode;
    }

    public String getContractorCorpName() {
        return contractorCorpName;
    }

    public void setContractorCorpName(String contractorCorpName) {
        this.contractorCorpName = contractorCorpName;
    }
}