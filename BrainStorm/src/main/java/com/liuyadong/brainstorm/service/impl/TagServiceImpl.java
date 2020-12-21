package com.liuyadong.brainstorm.service.impl;

import com.liuyadong.brainstorm.mapper.CategoryMapper;
import com.liuyadong.brainstorm.mapper.TagMapper;
import com.liuyadong.brainstorm.mapper.custom.ThoughtMapperCustom;
import com.liuyadong.brainstorm.mapper.custom.CategoryMapperCustom;
import com.liuyadong.brainstorm.mapper.custom.TagMapperCustom;
import com.liuyadong.brainstorm.entity.Tag;
import com.liuyadong.brainstorm.entity.custom.ThoughtCustom;
import com.liuyadong.brainstorm.entity.custom.ThoughtListVo;
import com.liuyadong.brainstorm.entity.custom.CategoryCustom;
import com.liuyadong.brainstorm.entity.custom.TagCustom;
import com.liuyadong.brainstorm.service.TagService;
import com.liuyadong.brainstorm.util.others.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class TagServiceImpl implements TagService {
	@Autowired
	private TagMapper tagMapper;

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private CategoryMapperCustom categoryMapperCustom;

	@Autowired
	private TagMapperCustom tagMapperCustom;

	@Autowired
	private ThoughtMapperCustom thoughtMapperCustom;

	//获得标签总数
	@Override
	public Integer countTag(Integer status) throws Exception {
		Integer tagCount = tagMapperCustom.countTag(status);
		return tagCount;
	}
	
	//获得标签列表
	@Override
	public List<TagCustom> listTag(Integer status) throws Exception {
		List<TagCustom> tagList = tagMapperCustom.listTag(status);
		for(int i=0;i<tagList.size();i++) {
			Integer tagId = tagList.get(i).getTagId();
			int count = thoughtMapperCustom.countThoughtByTag(status,tagId);
			tagList.get(i).setThoughtCount(count);
		}
		return tagList;
	}

	
	//获得含有该标签的想法列表
	@Override
	public List<ThoughtListVo> getThoughtListByPage(Integer status,Integer pageNow, Integer pageSize,Integer tagId) throws Exception {
		List<ThoughtListVo> thoughtListVoList = new ArrayList<ThoughtListVo>();
		List<ThoughtCustom> thoughtCustomList = new ArrayList<ThoughtCustom>();
		
		
		//获得该标签的具体信息
		Tag tag = tagMapper.selectByPrimaryKey(tagId);
		if(tag==null) {
			return null;
		}
		
		//分页显示
		Page page = null;

		int totalCount = thoughtMapperCustom.countThoughtByTag(status,tagId);
		if (pageNow != null) {
			page = new Page(totalCount, pageNow,pageSize);
			thoughtCustomList = tagMapperCustom.listThoughtWithTagByPage(status,tagId,page.getStartPos(), page.getPageSize());
		} else {
			page = new Page(totalCount, 1,pageSize);
			thoughtCustomList = tagMapperCustom.listThoughtWithTagByPage(status,tagId,page.getStartPos(), page.getPageSize());
		}
		
		for(int i=0;i<thoughtCustomList.size();i++) {
			ThoughtListVo thoughtListVo = new ThoughtListVo();
			//1、将想法装入 thoughtListVo
			ThoughtCustom thoughtCustom = thoughtCustomList.get(i);
			thoughtListVo.setThoughtCustom(thoughtCustom);
			//2、将分类信息装到 thoughtListVoList 中
			List<CategoryCustom> categoryCustomList = new ArrayList<CategoryCustom>();
			Integer cate =thoughtCustomList.get(i).getThoughtParentCategoryId();
			Integer cate2 =thoughtCustomList.get(i).getThoughtChildCategoryId();
			CategoryCustom categoryCustom = categoryMapperCustom.getCategoryById(status,cate);
			CategoryCustom categoryCustom2 = categoryMapperCustom.getCategoryById(status,cate2);
			if(categoryCustom!=null) {
				categoryCustomList.add(categoryCustom);
			}
			if(categoryCustom2!=null) {
				categoryCustomList.add(categoryCustom2);
			}
			thoughtListVo.setCategoryCustomList(categoryCustomList);
			
			thoughtListVoList.add(thoughtListVo);
		}
		//确保该标签有想法，防止空指针
		if(totalCount!=0) {
			//3、将Page存储在 thoughtListVoList 第一个元素中
			thoughtListVoList.get(0).setPage(page);

			//4、将标签信息 装入  thoughtListVoList 第一个元素中
			List<TagCustom> tagCustomList = new ArrayList<TagCustom>();
			TagCustom tagCustom = new TagCustom();
			BeanUtils.copyProperties(tag, tagCustom);
			tagCustomList.add(tagCustom);
			thoughtListVoList.get(0).setTagCustomList(tagCustomList);
		}
		return thoughtListVoList;
	}

    @Override
    public TagCustom getTagById(Integer id) throws Exception {
	    TagCustom tagCustom = new TagCustom();
	    Tag tag = tagMapper.selectByPrimaryKey(id);
	    if(tag==null) {
	    	return null;
		}
	    BeanUtils.copyProperties(tag,tagCustom);
	    return tagCustom;
    }

    @Override
    public void insertTag(Tag tag) throws Exception {
        tagMapper.insertSelective(tag);
    }

    @Override
    public void updateTag(Tag tag) throws Exception {
        tagMapper.updateByPrimaryKeySelective(tag);
    }

    @Override
    public void deleteTag(Integer id) throws Exception {
        tagMapper.deleteByPrimaryKey(id);
    }

	@Override
	public Tag getTagByName(String name) throws Exception {
		Tag tag = tagMapperCustom.getTagByName(name);
		return tag;
	}


}
