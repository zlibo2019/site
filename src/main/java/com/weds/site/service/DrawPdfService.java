package com.weds.site.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weds.site.entity.DtContractCountRsp;
import com.weds.site.entity.DtContractRsp;
import com.weds.site.mapper.DrawPdfMapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DrawPdfService {

    @Autowired
    private DrawPdfMapper drawPdfMapper;

    public List<Integer> selectAllCompany(){
        return drawPdfMapper.selectAllCompany();
    }
    
    public List<DtContractCountRsp> selectContractCount(Integer regSerial){
    	  return drawPdfMapper.selectContractCount(regSerial);
    }
    
    public List<DtContractRsp> selectAllUploadFile(Integer userSerial,Integer regSerial){
        return drawPdfMapper.selectAllUploadFile(userSerial,regSerial);
    }
    
    public int selectMoveContractToAttachment(Integer userSerial,Integer regSerial){ 
    	return  drawPdfMapper.selectMoveContractToAttachment(userSerial,regSerial);
    }
    
    public int moveContractToAttachment(Integer userSerial,Integer regSerial){ 
    	return  drawPdfMapper.moveContractToAttachment(userSerial,regSerial);
    }
    
    public int selectMoveContractToAttachmentByXh(DtContractRsp dtContractRsp){ 
    	return  drawPdfMapper.selectMoveContractToAttachmentByXh(dtContractRsp);
    }
    
    public int selectMoveContractToAttachmentStatusByXh(DtContractRsp dtContractRsp){ 
    	return  drawPdfMapper.selectMoveContractToAttachmentStatusByXh(dtContractRsp);
    }
    
    public int moveContractToAttachmentByXh(DtContractRsp dtContractRsp){ 
    	return  drawPdfMapper.moveContractToAttachmentByXh(dtContractRsp);
    }
    
    public int contractToSql(DtContractRsp dtContractRsp){ 
    	return  drawPdfMapper.contractToSql(dtContractRsp);
    }
    
    
    
}
