package com.weds.site.entity.req;

import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.base.CustomPageSearch;

public class ProjectTrainingReq extends CustomPageSearch {
    // 平台为项目分配的接入编号（必填）
    @NotNullAndEmptyValid
    private String projectCode;

    // 培训日期。格式yyyy-MM-dd
    private String trainingDate;

    // 培训类型代码
    private String typeCode;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(String trainingDate) {
        this.trainingDate = trainingDate;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
