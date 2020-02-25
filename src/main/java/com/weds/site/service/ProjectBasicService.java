package com.weds.site.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.reflect.TypeToken;
import com.weds.core.base.BaseService;
import com.weds.framework.core.common.model.JsonResult;
import com.weds.site.base.CommonService;
import com.weds.site.base.PageSearchRespEntity;
import com.weds.site.constant.SiteConstants;
import com.weds.site.entity.PartQueryEntity;
import com.weds.site.entity.ProjectBasicInfoAddEntity;
import com.weds.site.entity.ProjectBasicInfoPartUpdateEntity;
import com.weds.site.entity.ProjectBasicInfoQueryEntity;
import com.weds.site.entity.ProjectBasicInfoUpdateEntity;
import com.weds.site.entity.req.ProjectBasicInfoReq;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/projectBasicService")
@Api(description = "项目信息")
public class ProjectBasicService extends BaseService {

    @Autowired
    private CommonService commonService;

    @ApiOperation(value = "上传项目信息")
    @RequestMapping(value = "/projectAdd", method = RequestMethod.POST)
    public JsonResult<Object> projectAdd(@RequestBody @Valid ProjectBasicInfoAddEntity projectBasicInfoAddEntity) {
        return commonService.sendPostRequest(SiteConstants.PROJECT_ADD, projectBasicInfoAddEntity);
    }

    @ApiOperation(value = "修改项目信息")
    @RequestMapping(value = "/projectUpdate", method = RequestMethod.POST)
    public JsonResult<Object> projectUpdate(@RequestBody @Valid ProjectBasicInfoUpdateEntity projectBasicInfoUpdateEntity) {
        return commonService.sendPostRequest(SiteConstants.PROJECT_UPDATE, projectBasicInfoUpdateEntity);
    }
    
    @ApiOperation(value = "修改项目信息(一项目一密钥)")
    @RequestMapping(value = "/PartUpdate", method = RequestMethod.POST)
    public JsonResult<Object> PartUpdate(@RequestBody @Valid ProjectBasicInfoPartUpdateEntity projectBasicInfoPartUpdateEntity) {
        return commonService.sendPostRequest(SiteConstants.PROJECT_PARTUPDATE, projectBasicInfoPartUpdateEntity);
    }
    

    @ApiOperation(value = "查询项目信息")
    @RequestMapping(value = "/projectQuery", method = RequestMethod.POST)
    public JsonResult projectQuery(@RequestBody ProjectBasicInfoReq projectInfoReq) {
        TypeToken typeToken = new TypeToken<PageSearchRespEntity<ProjectBasicInfoQueryEntity>>() {
        };
        return commonService.sendPostQueryRequest(SiteConstants.PROJECT_QUERY, projectInfoReq, typeToken);
    }
    
    
    
    @ApiOperation(value = "查询项目信息(一项目一密钥)")
    @RequestMapping(value = "/partQuery", method = RequestMethod.POST)
    public JsonResult partQuery(@RequestBody ProjectBasicInfoReq projectInfoReq) {
        TypeToken typeToken = new TypeToken<PageSearchRespEntity<PartQueryEntity>>() {
        };
        return commonService.sendPostQueryRequest(SiteConstants.PROJECT_QUERY, projectInfoReq, typeToken);
    }
}
