package com.liuyadong.brainstorm.service.impl;

import com.liuyadong.brainstorm.entity.Page;
import com.liuyadong.brainstorm.mapper.PageMapper;
import com.liuyadong.brainstorm.mapper.custom.PageMapperCustom;
import com.liuyadong.brainstorm.entity.custom.PageCustom;
import com.liuyadong.brainstorm.service.PageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class PageServiceImpl implements PageService{
	@Autowired
	private PageMapperCustom pageMapperCustom;

	@Autowired
	private PageMapper pageMapper;

	@Override
	public PageCustom getPageByKey(Integer status,String key) throws Exception {
		PageCustom pageCustom = pageMapperCustom.getPageByKey(status,key);
		return pageCustom;
	}

	@Override
	public PageCustom getPageById(Integer id) throws Exception {
		Page page = pageMapper.selectByPrimaryKey(id);
		PageCustom pageCustom = new PageCustom();
		BeanUtils.copyProperties(page,pageCustom);
		return pageCustom;
	}

	@Override
	public List<PageCustom> listPage(Integer status) throws Exception {
		List<PageCustom> pageCustomList = pageMapperCustom.listPage(status);
		return pageCustomList;
	}


	@Override
	public void insertPage(Page page) throws Exception {
		pageMapper.insert(page);
	}

	@Override
	public void deletePage(Integer id) throws Exception {
		pageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updatePage(Page page) throws Exception {
		pageMapper.updateByPrimaryKeySelective(page);
	}
}
