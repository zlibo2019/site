package com.weds.site.entity;

import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

public class BuilderLicensesEntity {
    // 工程名称（必填）
    @NotNullAndEmptyValid
    private String prjName;

    // 施工许可证编号。AES（必填）
    @NotNullAndEmptyValid
    @ColumnProperties(aes = true)
    private String builderLicenseNum;

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    public String getBuilderLicenseNum() {
        return builderLicenseNum;
    }

    public void setBuilderLicenseNum(String builderLicenseNum) {
        this.builderLicenseNum = builderLicenseNum;
    }
}
