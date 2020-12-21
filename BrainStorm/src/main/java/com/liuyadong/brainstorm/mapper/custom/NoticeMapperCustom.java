package com.liuyadong.brainstorm.mapper.custom;

import com.liuyadong.brainstorm.entity.custom.NoticeCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface NoticeMapperCustom {
	
	//获得公告总数
	public Integer countNotice(@Param(value = "status") Integer status) throws Exception;
	
	//获得公告列表
	public List<NoticeCustom> listNotice(@Param(value = "status") Integer status) throws Exception;


}
