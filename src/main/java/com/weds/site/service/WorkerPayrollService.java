package com.weds.site.service;

import com.google.gson.reflect.TypeToken;
import com.weds.core.base.BaseService;
import com.weds.framework.core.common.model.JsonResult;
import com.weds.site.base.CommonService;
import com.weds.site.base.PageSearchRespEntity;
import com.weds.site.constant.SiteConstants;
import com.weds.site.entity.WorkerPayrollAddEntity;
import com.weds.site.entity.WorkerPayrollQueryEntity;
import com.weds.site.entity.req.WorkerPayrollReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/workerPayrollService")
@Api(description = "工人工资")
public class WorkerPayrollService extends BaseService {

    @Autowired
    private CommonService commonService;

    @ApiOperation(value = "上传工人工资")
    @RequestMapping(value = "/workerPayrollAdd", method = RequestMethod.POST)
    public





    JsonResult<Object> workerPayrollAdd(@RequestBody @Valid WorkerPayrollAddEntity workerPayrollEntity) {
        return commonService.sendPostRequest(SiteConstants.PAYROLL_ADD, workerPayrollEntity);
    }

    @ApiOperation(value = "查询工人工资")
    @RequestMapping(value = "/workerPayrollQuery", method = RequestMethod.POST)
    public JsonResult workerPayrollQuery(@RequestBody WorkerPayrollReq workerPayrollReq) {
        TypeToken typeToken = new TypeToken<WorkerPayrollQueryEntity>() {
        };
        return commonService.sendPostQueryRequest(SiteConstants.PAYROLL_QUERY, workerPayrollReq, typeToken);
    }
}
