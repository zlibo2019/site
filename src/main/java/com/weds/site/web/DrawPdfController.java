package com.weds.site.web;


import com.alibaba.fastjson.JSONObject;
import com.weds.core.annotation.Logs;
import com.weds.core.base.BaseController;
import com.weds.core.utils.StringUtils;
import com.weds.core.utils.coder.Coder;
import com.weds.framework.core.common.model.CommPager;
import com.weds.framework.core.common.model.JsonResult;
import com.weds.framework.core.common.model.PageSearch;
import com.weds.site.entity.DtContractCountRsp;
import com.weds.site.entity.DtContractRsp;
import com.weds.site.service.DrawPdfService;
import com.weds.site.util.PrintToPdfUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.utils.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;


import javax.imageio.stream.FileImageOutputStream;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;




@RestController
@RequestMapping(value = "/pdf")
@Api(value = "绘制pdf", description = "绘制pdf")
public class DrawPdfController extends BaseController {

    @Autowired
    private DrawPdfService drawPdfService;

    @Value("${contractUrl}")
    private String contractUrl;
    
    @Value("${contractAttachmentUrl}")
    private String contractAttachmentUrl;

    /**
     * 将多张图片合并转为PDF；需要用到iTextpdf包，
     * 
     * @author sjm
     * 
     */
    //@ApiOperation(value = "上传照片", notes = "上传照片")
    //@RequestMapping(value = "/drawPdf", method = RequestMethod.POST)
    @Scheduled(fixedDelay =1200000 )//1h   
    public void insertPhoto() {  	
    	//pdf复制到新表
    	//doc复制到新表
    	//单张图片不生成，复制到新表
    	//两张及两张以上的图片生成pdf，插入到新表
    	
    	///先查询出来所有企业
    	List<Integer> companys=drawPdfService.selectAllCompany();
    	//循环所有企业
        for (Integer regSerial : companys) {
			//查询企业下每个人上传的合同图片数量
        	List<DtContractCountRsp> contractCount=drawPdfService.selectContractCount(regSerial);
        	//循环企业下每个人上传的合同图片数量
        	for (Iterator iterator = contractCount.iterator(); iterator.hasNext();) {
        		DtContractCountRsp dtContractCountRsp = (DtContractCountRsp) iterator.next();
				//只上传了一个图片
        		if(dtContractCountRsp.getUserContractCount()==1){
        				//移动到合同附件表
        			    if(drawPdfService.selectMoveContractToAttachment(dtContractCountRsp.getUserSerial(), regSerial)<1){
        				drawPdfService.moveContractToAttachment(dtContractCountRsp.getUserSerial(),regSerial);
        			    }
				}
        		///两张图片以上，或者两张图片，或者两个doc,或者两个pdf 以上
        		List<DtContractRsp> tupian=new ArrayList<DtContractRsp>();
        		if(dtContractCountRsp.getUserContractCount()>1){
					//根据user_serial reg_serial 获取记录信息 按照时间倒序
         		    List<DtContractRsp> contracts=drawPdfService.selectAllUploadFile(dtContractCountRsp.getUserSerial(),regSerial);
         		    //总数量
         		    Integer sumCount=0;
         		    //循环每个人的
        		    for (int i = 0; i < contracts.size(); i++) {
						DtContractRsp dtContractRsp = (DtContractRsp) contracts.get(i);
						//如果是pdf
						System.out.println(dtContractRsp.getContractName().substring(dtContractRsp.getContractName().lastIndexOf(".") + 1));
						if(dtContractRsp.getContractName().substring(dtContractRsp.getContractName().lastIndexOf(".") + 1).equals("pdf")){
							//如果总数量=2 break
							if(sumCount==2){
								break;
							}else{
								//移动到附件表
								if(drawPdfService.selectMoveContractToAttachmentByXh(dtContractRsp)<1){
								drawPdfService.moveContractToAttachmentByXh(dtContractRsp);
								}
								//总数量+1
								sumCount+=1;
							}
						}
						//如果是doc 或者docx
						if(dtContractRsp.getContractName().substring(dtContractRsp.getContractName().lastIndexOf(".") + 1).equals("doc")
								|| dtContractRsp.getContractName().substring(dtContractRsp.getContractName().lastIndexOf(".") + 1).equals("docx")){
							//如果总数量=2 break
							if(sumCount==2){
								break;
							}else{
								//移动到附件表
								if(drawPdfService.selectMoveContractToAttachmentByXh(dtContractRsp)<1){
								drawPdfService.moveContractToAttachmentByXh(dtContractRsp);
								}
								//总数量+1
								sumCount+=1;
							}
						}
						//如果是图片
						if(dtContractRsp.getContractName().substring(dtContractRsp.getContractName().lastIndexOf(".") + 1).equals("png") 
			        					|| dtContractRsp.getContractName().substring(dtContractRsp.getContractName().lastIndexOf(".") + 1).equals("jpg")
			        					|| dtContractRsp.getContractName().substring(dtContractRsp.getContractName().lastIndexOf(".") + 1).equals("gif")
			        					|| dtContractRsp.getContractName().substring(dtContractRsp.getContractName().lastIndexOf(".") + 1).equals("jpeg")
			        					|| dtContractRsp.getContractName().substring(dtContractRsp.getContractName().lastIndexOf(".") + 1).equals("tif")){
							//如果总数量=0 全是图片 这时候把所有图片都压缩到pdf中，然后插入附件表
							//如果总数量=1 已经有一个pdf/doc了，这时候吧剩下图片压缩到pdf中 ，然后插入附件表
							if(sumCount<2){
								tupian.add(dtContractRsp);
							}else{
								//如果总数量=2 已经有两个文档了，这里不处理了 break
								break;
							}
						}
					}
        		    //如果有图片需要压成pdf 压缩图片 ，压完了，写入附件表
        		    DtContractRsp contractToDb=new DtContractRsp();
        		    if(tupian.size()>0){
        		    	//调用工具类压缩图片到pdf
                        File[] filsStrs=new File[tupian.size()];
                        String pdfPath="";
                        int picnum=0;
                        for (int i=0;i<tupian.size();i++){  
                        	//../contract/500043/20000/
                        	//图片路径contractUrl
                            String imgFilePath=contractUrl+regSerial+"/"+Integer.valueOf(tupian.get(i).getUserSerial())/1000+"/"+tupian.get(i).getContractName();
                            //生成的pdf路径contractAttachmentUrl
                            pdfPath=contractAttachmentUrl+regSerial+"/"+Integer.valueOf(tupian.get(i).getUserSerial())/1000+"/"+tupian.get(i).getUserSerial()+".pdf";                           
                            File contractFile=new File(imgFilePath);//判断合同文件是否创建成功
                            if(contractFile.exists()){
                                System.out.println("the same name file exists, can not create dir");
                               //添加文件
                                filsStrs[i]=contractFile;
                            }else {
                            	picnum+=1;
                            	System.out.println(imgFilePath+"图片文件不存在");
                            }
                            contractToDb.setUserSerial(tupian.get(i).getUserSerial());
                            contractToDb.setRegSerial(tupian.get(i).getRegSerial());
                            contractToDb.setContractName(tupian.get(i).getUserSerial()+".pdf");
                            contractToDb.setContractPath("../contractAttachment/"+regSerial+"/"+Integer.valueOf(tupian.get(i).getUserSerial())/1000+"/");
                            contractToDb.setContractBh(i+1);
                        }
                        //要写入pdf的图片数量和 数据库重图片的数量一致 
                        if(filsStrs.length==tupian.size() && picnum==0){
                         	//存在记录但是状态不是1，也就是没发送的时候都需要生成
/*                        	if(drawPdfService.selectMoveContractToAttachmentStatusByXh(contractToDb)>0){
	                        ///这里调用图片生成pdf工具类，把多张图片生成pdf
	                        PrintToPdfUtil.toPdf(filsStrs, pdfPath);
                        	}*/
	                        //写入数据库，//写入附件表
	                        contractToDb.setLx(0);
	                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	                        ///当前时间
	                        contractToDb.setSj(df.format(new Date()));
	                        if(drawPdfService.selectMoveContractToAttachmentByXh(contractToDb)<1){
		                        //不存在生成/这里调用图片生成pdf工具类，把多张图片生成pdf
		                        PrintToPdfUtil.toPdf(filsStrs, pdfPath); 
	                        int contractToSql = drawPdfService.contractToSql(contractToDb);//插入数据库
	                        }
	        		    	//总数量+1
	        		    	sumCount+=1;
                        }
        		   }
        		}
        	//===================================================================
			}
		}
    }
 
    
}