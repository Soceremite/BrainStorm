package com.liuyadong.brainstorm.mapper.custom;


import com.liuyadong.brainstorm.entity.Tag;
import com.liuyadong.brainstorm.entity.custom.ThoughtCustom;
import com.liuyadong.brainstorm.entity.custom.TagCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TagMapperCustom {
	//获得标签总数
	public Integer countTag(@Param(value = "status") Integer status) throws Exception;
	
	//获得标签列表
	public List<TagCustom> listTag(@Param(value = "status")Integer status) throws Exception;

	//获得带有该标签的想法列表
	public List<ThoughtCustom> listThoughtWithTagByPage(@Param(value = "status") Integer status,@Param(value = "tagId") Integer tagId, @Param(value="startPos") Integer startPos, @Param(value="pageSize") Integer pageSize) throws Exception;

	//根据标签名获取标签
	public Tag  getTagByName(String name) throws Exception;

}
