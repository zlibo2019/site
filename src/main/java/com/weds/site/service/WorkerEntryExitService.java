package com.weds.site.service;

import com.google.gson.reflect.TypeToken;
import com.weds.core.base.BaseService;
import com.weds.framework.core.common.model.JsonResult;
import com.weds.site.base.CommonService;
import com.weds.site.base.PageSearchRespEntity;
import com.weds.site.constant.SiteConstants;
import com.weds.site.entity.WorkerEntryExitAddEntity;
import com.weds.site.entity.WorkerEntryExitQueryEntity;
import com.weds.site.entity.req.WorkerEntryExitReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/workerEntryExitService")
@Api(description = "目工人进/退场信息")
public class WorkerEntryExitService extends BaseService {

    @Autowired
    private CommonService commonService;

    @ApiOperation(value = "上传项目工人进/退场信息")
    @RequestMapping(value = "/workerEntryExitAdd", method = RequestMethod.POST)
    public JsonResult<Object> workerEntryExitAdd(@RequestBody @Valid WorkerEntryExitAddEntity workerEntryExitAddEntity) {
        return commonService.sendPostRequest(SiteConstants.WORKERENTRYEXIT_ADD, workerEntryExitAddEntity);
    }

    @ApiOperation(value = "查询项目工人进/退场信息")
    @RequestMapping(value = "/workerEntryExitQuery", method = RequestMethod.POST)
    public JsonResult workerEntryExitQuery(@RequestBody WorkerEntryExitReq workerEntryExitReq) {
        TypeToken typeToken = new TypeToken<PageSearchRespEntity<WorkerEntryExitQueryEntity>>() {
        };
        return commonService.sendPostQueryRequest(SiteConstants.WORKERENTRYEXIT_QUERY, workerEntryExitReq, typeToken);
    }
}
