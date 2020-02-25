package com.weds.site.entity;

import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

public class BankInfosEntity {
    // 银行代码。参考银行代码字典表（必填）
    @NotNullAndEmptyValid
    private String bankCode;

    // 银行支行名称（必填）
    @NotNullAndEmptyValid
    private String bankName;

    // 银行卡号。AES（必填）
    @NotNullAndEmptyValid
    @ColumnProperties(aes = true)
    private String bankNumber;

    // 银行联号
    private String bankLinkNumber;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getBankLinkNumber() {
        return bankLinkNumber;
    }

    public void setBankLinkNumber(String bankLinkNumber) {
        this.bankLinkNumber = bankLinkNumber;
    }
}
