package com.weds.site.mapper;


import com.weds.framework.core.annotation.MyBatisDao;
import com.weds.site.entity.DtContractCountRsp;
import com.weds.site.entity.DtContractRsp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


@MyBatisDao
public interface DrawPdfMapper {

	//查询所有企业
	List<Integer> selectAllCompany();
	
	///企业下每个人的合同图片数量
	List<DtContractCountRsp> selectContractCount(@Param("regSerial")Integer regSerial);
	
    //查询合同照片
	List<DtContractRsp> selectAllUploadFile(@Param("userSerial") Integer userSerial,@Param("regSerial") Integer regSerial);

	int selectMoveContractToAttachment(@Param("userSerial") Integer userSerial,@Param("regSerial") Integer regSerial);
    ///移动图片到附件表
    int moveContractToAttachment(@Param("userSerial") Integer userSerial,@Param("regSerial") Integer regSerial);
    
    
    int selectMoveContractToAttachmentByXh(DtContractRsp dtContractRsp);
    
    
    int selectMoveContractToAttachmentStatusByXh(DtContractRsp dtContractRsp);
    ///移动图片到附件表根据序号主键
    int moveContractToAttachmentByXh(DtContractRsp dtContractRsp); 
    
    ///生产pdf后插入数据库合同附件表
    int contractToSql(DtContractRsp dtContractRsp); 
    
    
    
}