package com.weds.site.service;

import com.google.gson.reflect.TypeToken;
import com.weds.core.base.BaseService;
import com.weds.framework.core.common.model.JsonResult;
import com.weds.site.base.CommonService;
import com.weds.site.base.PageSearchRespEntity;
import com.weds.site.constant.SiteConstants;
import com.weds.site.entity.CorpInfoEntity;
import com.weds.site.entity.ProjectContractorEntity;
import com.weds.site.entity.req.ProjectContractorReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/projectContractorService")
@Api(description = "项目参建单位信息")
public class ProjectContractorService extends BaseService {

    @Autowired
    private CommonService commonService;

    @ApiOperation(value = "上传项目参建单位信息")
    @RequestMapping(value = "/projectContractorAdd", method = RequestMethod.POST)
    public JsonResult<Object> projectContractorAdd(@RequestBody @Valid ProjectContractorEntity projectContractorEntity) {
        return commonService.sendPostRequest(SiteConstants.PROJECTSUBCONTRACTOR_ADD, projectContractorEntity);
    }

    @ApiOperation(value = "修改项目参建单位信息")
    @RequestMapping(value = "/projectContractorUpdate", method = RequestMethod.POST)
    public JsonResult<Object> projectContractorUpdate(@RequestBody @Valid ProjectContractorEntity projectContractorEntity) {
        return commonService.sendPostRequest(SiteConstants.PROJECTSUBCONTRACTOR_UPDATE, projectContractorEntity);
    }

    @ApiOperation(value = "查询项目参建单位信息")
    @RequestMapping(value = "/projectContractorQuery", method = RequestMethod.POST)
    public JsonResult projectContractorQuery(@RequestBody @Valid ProjectContractorReq projectContractorReq) {
        TypeToken typeToken = new TypeToken<PageSearchRespEntity<ProjectContractorEntity>>() {
        };
        return commonService.sendPostQueryRequest(SiteConstants.PROJECTSUBCONTRACTOR_QUERY, projectContractorReq, typeToken);
    }
}
