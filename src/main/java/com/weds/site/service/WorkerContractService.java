package com.weds.site.service;

import com.google.gson.reflect.TypeToken;
import com.weds.core.base.BaseService;
import com.weds.framework.core.common.model.JsonResult;
import com.weds.site.base.CommonService;
import com.weds.site.base.PageSearchRespEntity;
import com.weds.site.constant.SiteConstants;
import com.weds.site.entity.WorkerContractAddEntity;
import com.weds.site.entity.WorkerContractQueryEntity;
import com.weds.site.entity.req.WorkerContractReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/workerContractService")
@Api(description = "工人合同信息")
public class WorkerContractService extends BaseService {

    @Autowired
    private CommonService commonService;

    @ApiOperation(value = "上传工人合同信息")
    @RequestMapping(value = "/workerContractAdd", method = RequestMethod.POST)
    public JsonResult<Object> workerContractAdd(@RequestBody @Valid WorkerContractAddEntity workerContractEntity) {
        return commonService.sendPostRequest(SiteConstants.WORKERCONTRACT_ADD, workerContractEntity);
    }

    @ApiOperation(value = "查询工人合同信息")
    @RequestMapping(value = "/workerContractQuery", method = RequestMethod.POST)
    public JsonResult workerContractQuery(@RequestBody WorkerContractReq workerContractReq) {
        TypeToken typeToken = new TypeToken<PageSearchRespEntity<WorkerContractQueryEntity>>() {
        };
        return commonService.sendPostQueryRequest(SiteConstants.WORKERCONTRACT_QUERY, workerContractReq, typeToken);
    }
}
