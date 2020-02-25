package com.weds.site.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weds.core.base.BaseService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/run")
@Api(description = "接口执行")
public class RunController extends BaseService {

 @Autowired DataDockingController dataDockingController;
 
 @Autowired HaiYangController haiYangController;

    @Scheduled(fixedDelay = 3600000)//1h
    public void run(){
        try {
 
        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        	System.out.println("s1-------"+df.format(new Date()));// new Date()为获取当前系统时间
   
        	dataDockingController.QueryWaitInfo();//10-等待处理的记录	
        	Thread.sleep(5000);
        	dataDockingController.SendCorpInfo();//0-企业
          	Thread.sleep(5000);
//       	    dataDockingController.SendProBasicInfo();//1-项目
//       	 	Thread.sleep(5000);
        	dataDockingController.SendProContractorInfo();//3-参建单位信息 
        	Thread.sleep(5000);
        	dataDockingController.SendTeamInfo();//5-班组
        	Thread.sleep(5000);
        	dataDockingController.SendWorkerInfo();//2-人员
        	Thread.sleep(5000);
        	dataDockingController.SendWorkerEntryInfo();//6-工人进场 
        	Thread.sleep(5000);
        	dataDockingController.SendWorkerContractInfo();//7-工人合同
        	Thread.sleep(5000);
        	dataDockingController.SendWorkerAttendanceInfo();//8-工人考勤
        	Thread.sleep(5000);
        	dataDockingController.SendWorkerExitInfo();//6-工人退场 
        	Thread.sleep(5000);
        	dataDockingController.SendProTrainingInfo();//4-培训信息
        	Thread.sleep(5000);
        	dataDockingController.SendPayrollInfo();//9-工资
        	Thread.sleep(5000);
        	haiYangController.uploadPersonDetails();//10-海阳工地上传浪潮工人信息
        	Thread.sleep(5000);
        	haiYangController.uploadAttendanceInfoPort();//11-海阳工地上传工人考勤记录
        	Thread.sleep(5000);
        }catch (Exception a){
            a.printStackTrace();
            System.err.println("run Excepation ");
        }

    }


}
