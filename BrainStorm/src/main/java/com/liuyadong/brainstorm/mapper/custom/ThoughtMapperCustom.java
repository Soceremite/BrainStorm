package com.liuyadong.brainstorm.mapper.custom;

import com.liuyadong.brainstorm.entity.custom.ThoughtCustom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface ThoughtMapperCustom {
	//获取想法总数
	public Integer countThought(@Param(value="status") Integer status) throws Exception;
	
	//获得留言总数
	public Integer countThoughtComment(@Param(value="status")Integer status) throws Exception;
	
	//获得浏览量总数
	public Integer countThoughtView(@Param(value="status")Integer status) throws Exception;
	
	//获得所有想法(想法归档)
	public List<ThoughtCustom> listThought(@Param(value="status")Integer status) throws Exception;
	
	//根据id查询用户信息
	public ThoughtCustom getThoughtById(@Param(value="status")Integer status,@Param(value="id")Integer id) throws Exception;
	
	//分页操作
	public List<ThoughtCustom> listThoughtByPage(@Param(value="status") Integer status,@Param(value="startPos") Integer startPos, @Param(value="pageSize") Integer pageSize) throws Exception;
	
	//想法结果查询结果的数量
	public Integer getSearchResultCount(@Param(value="status") Integer status,@Param(value="query")String query) throws Exception;
	
	//查询想法分页操作
	public List<ThoughtCustom> listSearchResultByPage(@Param(value="status") Integer status,@Param(value="query") String query,@Param(value="startPos") Integer startPos,@Param(value="pageSize") Integer pageSize) throws Exception;
	
	//获得同分类的想法(相关想法)
	public List<ThoughtCustom> listThoughtWithSameCategory(
			@Param(value="status") Integer status,
			 @Param(value = "parentCategory") Integer parentCategory
			,@Param(value = "childCategory") Integer childCategory
			,@Param(value = "limit") Integer limit
	) throws Exception;
	
	//获得访问最多的想法(猜你喜欢)
	public List<ThoughtCustom> listThoughtByViewCount(@Param(value="status") Integer status,@Param(value = "limit") Integer limit) throws Exception;
	
	//获得上一篇想法
	public ThoughtCustom getAfterThought(@Param(value="status") Integer status,@Param(value="id") Integer id) throws Exception;
	
	//获得下一篇想法
	public ThoughtCustom getPreThought(@Param(value="status") Integer status,@Param(value="id") Integer id) throws Exception;
	
	//获得该分类的想法数
	public Integer countThoughtByCategory(@Param(value="status") Integer status,@Param(value = "id") Integer id) throws Exception;

	//获得该分类的想法数
	public Integer countThoughtByTag(@Param(value="status") Integer status,@Param(value = "id") Integer id) throws Exception;

	//更新想法的评论数
	public void updateCommentCount(@Param(value = "thoughtId") Integer thoughtId) throws Exception;

	//获得最后更新的记录
	public ThoughtCustom getLastUpdateThought() throws Exception;
}



