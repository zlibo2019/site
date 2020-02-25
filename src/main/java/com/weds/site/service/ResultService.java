package com.weds.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.weds.core.base.BaseService;
import com.weds.framework.core.common.model.JsonResult;
import com.weds.site.base.CommonService;
import com.weds.site.entity.QueryResultEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/resultService")
@Api(description = "查询结果")
public class ResultService extends BaseService {

    @Autowired
    private CommonService commonService;
 
    @ApiOperation(value = "查询上传结果")
    @RequestMapping(value = "/queryResult", method = RequestMethod.POST)
    public JsonResult<Object> queryResult(@RequestBody QueryResultEntity queryResultEntity) {
        return commonService.sendPostRequest("AsyncHandleResult.Query", queryResultEntity);
    }
}
