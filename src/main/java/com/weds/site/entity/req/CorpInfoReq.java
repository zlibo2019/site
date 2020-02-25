package com.weds.site.entity.req;

import com.weds.site.base.CustomPageSearch;

public class CorpInfoReq extends CustomPageSearch {

    private String corpName;

    private String corpCode;

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }
}
