package com.weds.site.web;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import com.weds.core.annotation.Logs;
import com.weds.core.utils.coder.Coder;
import com.weds.framework.core.common.model.JsonResult;
import com.weds.site.entity.DockingQueryResultEntity;
import com.weds.site.service.DataDockingService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/send")
public class DataDockingController {
	@Autowired
	private DataDockingService dataDockingService;
	
	@Autowired
	private HttpServletResponse response;
	
    @Value("${fileUrl}")
    private String fileUrl;
	
//    http:\\47.104.157.222:8088\getPdfFile\contractAttachment\\500080\\20001\\20001805.pdf
    
/*	public static void main(String[] args) {
		System.out.println(URLEncoder.encode(Coder.encryptBASE64("contractAttachment\\500080\\20001\\gz.png".getBytes())));
	}*/
	@ApiOperation(value = "文件上传", notes = "")
    @RequestMapping(value = "/getPdfFile/{pathId}", method = RequestMethod.GET)
    public void getPdfFile(@PathVariable(value = "pathId") String pathId) {
		String path = new String(Coder.decryptBASE64(URLDecoder.decode(pathId)));
//		path="F:\\工地程序\\标准工地20181130\\源码-中建对接\\wwwroot\\"+path;
//		path="D:\\软件\\建筑用工管理系统V1.03\\wwwroot\\"+path;
		
//		String str2 = fileUrl.substring(fileUrl.length()-1, fileUrl.length()) ;
//		if(!str2.equals("/")) {fileUrl=fileUrl+"/";}
		path=fileUrl+path;
//		path="http:\\\\47.104.157.222:8088\\"+path;
//		System.out.println("path"+path);
    	String pdfPath = "";
    	File file = new File(path);
    	
    	String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        suffix = suffix.toUpperCase();
        
        if( suffix.equals("PDF")) {
        	response.setContentType("application/pdf");
        }else if(suffix.equals("JPG") ) {
        	response.setContentType("application/x-jpg");
//        }else if(suffix.equals("PNG") ) {
//        	System.out.println("type-PNG");
//        	response.setContentType("application/x-png");
        }else if(suffix.equals("DOC")   ) {
        	response.setContentType("application/msword");
//        }else if( suffix.equals("DOCX") ) {
//        	System.out.println("type-DOCX");
//        	response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        }
        
    	try {
    		IOUtils.write(FileUtils.readFileToByteArray(file), response.getOutputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}
 
    }
	
	
	

	@ApiOperation(value = "上传企业信息", notes = "")
	@PostMapping("/SendCorpInfo")
	public void SendCorpInfo() throws Exception {

		dataDockingService.SendCorpInfo();

	}

	@ApiOperation(value = "上传项目信息", notes = "")
	@PostMapping("/SendProBasicInfo")
	public void SendProBasicInfo() throws Exception {

		dataDockingService.SendProBasicInfo();

	}
	
	
	@ApiOperation(value = "上传参建单位信息", notes = "")
	@PostMapping("/SendProContractorInfo")
	public void SendProContractorInfo() throws Exception {

		dataDockingService.SendProContractorInfo();

	}
	
	
	@ApiOperation(value = "上传班组信息", notes = "")
	@PostMapping("/SendTeamInfo")
	public void SendTeamInfo() throws Exception {

		dataDockingService.SendTeamInfo();

	}
	
	
	@ApiOperation(value = "上传工人信息", notes = "")
	@PostMapping("/SendWorkerInfo")
	public void SendWorkerInfo() throws Exception {

		dataDockingService.SendWorkerInfo();

	}
	
	
	
	@ApiOperation(value = "上传工人合同信息", notes = "")
	@PostMapping("/SendWorkerContractInfo")
	public void SendWorkerContractInfo() throws Exception {

		dataDockingService.SendWorkerContractInfo();

	}
	
	
	@ApiOperation(value = "上传工人考勤信息", notes = "")
	@PostMapping("/SendWorkerAttendanceInfo")
	public void SendWorkerAttendanceInfo() throws Exception {

		dataDockingService.SendWorkerAttendanceInfo();

	}
	
	
	@ApiOperation(value = "上传工人进场信息", notes = "")
	@PostMapping("/SendWorkerEntryInfo")
	public void SendWorkerEntryInfo() throws Exception {

		dataDockingService.SendWorkerEntryInfo();

	}
	
	
	
	@ApiOperation(value = "上传工人退场信息", notes = "")
	@PostMapping("/SendWorkerExitInfo")
	public void SendWorkerExitInfo() throws Exception {

		dataDockingService.SendWorkerExitInfo();

	}
	
	
	@ApiOperation(value = "上传工人工资信息", notes = "")
	@PostMapping("/SendPayRollInfo")
	public void SendPayrollInfo() throws Exception {

		dataDockingService.SendPayrollInfo();
//		dataDockingService.SendPayrollInfoByUser();

	}
	
	
	
	@ApiOperation(value = "上传培训信息", notes = "")
	@PostMapping("/SendProTrainingInfo")
	public void SendProTrainingInfo() throws Exception {

		dataDockingService.SendProTrainingInfo();

	}
	
	
	@ApiOperation(value = "查询上传项目编码", notes = "")
	@PostMapping("/QueryProBasicCode")
	public void QueryProBasicCode(@RequestBody DockingQueryResultEntity dockingQueryResultEntity) throws Exception {

		dataDockingService.QueryProBasicCode(dockingQueryResultEntity);

	}

	
	
	
	@ApiOperation(value = "查询等待处理的记录", notes = "")
	@PostMapping("/QueryWaitInfo")
	public void QueryWaitInfo() throws Exception {

		dataDockingService.GetRuesultInfo();

	}
	
	
}
