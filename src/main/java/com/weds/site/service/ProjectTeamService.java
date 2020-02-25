package com.weds.site.service;

import com.google.gson.reflect.TypeToken;
import com.weds.core.base.BaseService;
import com.weds.framework.core.common.model.JsonResult;
import com.weds.site.base.CommonService;
import com.weds.site.base.PageSearchRespEntity;
import com.weds.site.constant.SiteConstants;
import com.weds.site.entity.ProjectTeamAddEntity;
import com.weds.site.entity.ProjectTeamQueryEntity;
import com.weds.site.entity.ProjectTeamUpdateEntity;
import com.weds.site.entity.req.ProjectTeamReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/projectTeamService")
@Api(description = "班组信息")
public class ProjectTeamService extends BaseService {

    @Autowired
    private CommonService commonService;

    @ApiOperation(value = "上传班组信息")
    @RequestMapping(value = "/teamAdd", method = RequestMethod.POST)
    public JsonResult<Object> teamAdd(@RequestBody @Valid ProjectTeamAddEntity projectTeamAddEntity) {
        return commonService.sendPostRequest(SiteConstants.TEAM_ADD, projectTeamAddEntity);
    }

    @ApiOperation(value = "修改班组信息")
    @RequestMapping(value = "/teamUpdate", method = RequestMethod.POST)
    public JsonResult<Object> teamUpdate(@RequestBody @Valid ProjectTeamUpdateEntity projectTeamUpdateEntity) {
        return commonService.sendPostRequest(SiteConstants.TEAM_UPDATE, projectTeamUpdateEntity);
    }

    @ApiOperation(value = "查询班组信息")
    @RequestMapping(value = "/teamQuery", method = RequestMethod.POST)
    public JsonResult teamQuery(@RequestBody ProjectTeamReq projectTeamReq) {
        TypeToken typeToken = new TypeToken<PageSearchRespEntity<ProjectTeamQueryEntity>>() {
        };
        return commonService.sendPostQueryRequest(SiteConstants.TEAM_QUERY, projectTeamReq, typeToken);
    }
}
