package com.weds.site.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weds.site.service.HaiyangService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/upload")
public class HaiYangController {
	@Autowired
	private HaiyangService haiyangService;

	@Autowired
	private HttpServletResponse response;

	@ApiOperation(value = "上传工人信息", notes = "")
	@PostMapping("/UploadPersonDetails")
	public void uploadPersonDetails()  {

		haiyangService.uploadPersonDetails();

	}

	@ApiOperation(value = "上传工人考勤信息", notes = "")
	@PostMapping("/UploadAttendanceInfoPort")
	public void uploadAttendanceInfoPort() throws Exception {

		haiyangService.uploadAttendanceInfoPort();

	}

}
