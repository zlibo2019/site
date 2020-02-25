package com.weds.site.entity;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

import java.util.List;

public class WorkerContractAddEntity extends BaseEntity {
    // 项目编码（必填）
    @NotNullAndEmptyValid
    private String projectCode;

    // 合同列表数据,JSON数组，数量不能超过5（必填）
    @ColumnProperties(aes = true)
    private List<ContractListEntity> contractList;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public List<ContractListEntity> getContractList() {
        return contractList;
    }

    public void setContractList(List<ContractListEntity> contractList) {
        this.contractList = contractList;
    }
}
