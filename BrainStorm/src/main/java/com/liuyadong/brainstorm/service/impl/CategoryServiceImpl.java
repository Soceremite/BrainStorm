package com.liuyadong.brainstorm.service.impl;

import com.liuyadong.brainstorm.mapper.CategoryMapper;
import com.liuyadong.brainstorm.mapper.custom.ThoughtMapperCustom;
import com.liuyadong.brainstorm.mapper.custom.CategoryMapperCustom;
import com.liuyadong.brainstorm.entity.Category;
import com.liuyadong.brainstorm.entity.custom.ThoughtCustom;
import com.liuyadong.brainstorm.entity.custom.ThoughtListVo;
import com.liuyadong.brainstorm.entity.custom.CategoryCustom;
import com.liuyadong.brainstorm.service.CategoryService;
import com.liuyadong.brainstorm.util.others.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 用户管理
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryMapperCustom categoryMapperCustom;
	
	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private ThoughtMapperCustom thoughtMapperCustom;

	@Override
	public Integer countCategory(Integer status) throws Exception {
		Integer categoryCount = categoryMapperCustom.countCategory(status);
		return categoryCount;
	}
	
	@Override
	public List<CategoryCustom> listCategory(Integer status) throws Exception {
		List<CategoryCustom> categoryCustomList = categoryMapperCustom.listCategory(status);
		for(int i=0;i<categoryCustomList.size();i++) {
			Integer cateId = categoryCustomList.get(i).getCategoryId();
			Integer count = thoughtMapperCustom.countThoughtByCategory(status,cateId);
			categoryCustomList.get(i).setThoughtCount(count);
		}

		return categoryCustomList;
	}
	
	@Override
	public List<ThoughtListVo> listThoughtWithCategoryByPage(Integer status,Integer pageNow, Integer pageSize,Integer cateId) throws Exception {
		List<ThoughtListVo> thoughtListVoList = new ArrayList<ThoughtListVo>();
		List<ThoughtCustom> thoughtCustomList = new ArrayList<ThoughtCustom>();
		
		//获得分类的信息
		Category category = categoryMapper.selectByPrimaryKey(cateId);
		//如果没有这个分类，返回null
		if(category==null) {
			return null;
		}

		//分页显示
		Page page = null;
		int totalCount = thoughtMapperCustom.countThoughtByCategory(status,cateId);

		if (pageNow != null) {
			page = new Page(totalCount, pageNow,pageSize);
			thoughtCustomList = categoryMapperCustom.listThoughtWithCategoryByPage(status,cateId,page.getStartPos(), page.getPageSize());
		} else {
			page = new Page(totalCount, 1,pageSize);
			thoughtCustomList = categoryMapperCustom.listThoughtWithCategoryByPage(status,cateId,page.getStartPos(), page.getPageSize());
			
		}
		for(int i=0;i<thoughtCustomList.size();i++) {
			ThoughtListVo thoughtListVo = new ThoughtListVo();
			
			//1、将想法信息装入 thoughtListVo
			ThoughtCustom thoughtCustom = thoughtCustomList.get(i);
			thoughtListVo.setThoughtCustom(thoughtCustom);


			//2、将分类信息装到 thoughtListVoList 中
			List<CategoryCustom> categoryCustomList = new ArrayList<CategoryCustom>();
			Integer parentCategoryId =thoughtCustomList.get(i).getThoughtParentCategoryId();
			Integer childCategoryId =thoughtCustomList.get(i).getThoughtChildCategoryId();
			CategoryCustom categoryCustom = categoryMapperCustom.getCategoryById(status, parentCategoryId);
			CategoryCustom categoryCustom2 = categoryMapperCustom.getCategoryById(status,childCategoryId);
			if(categoryCustom!=null) {
				categoryCustomList.add(categoryCustom);
			}
			if(categoryCustom2!=null) {
				categoryCustomList.add(categoryCustom2);
			}
			thoughtListVo.setCategoryCustomList(categoryCustomList);

			thoughtListVoList.add(thoughtListVo);
		}
        //如果该分类还没有想法
        if(totalCount!=0) {
            //2、将Page信息存储在第一个元素中
            thoughtListVoList.get(0).setPage(page);
        }
		return thoughtListVoList;
	}

	@Override
	public CategoryCustom getCategory(Integer status,Integer id) throws Exception {
		CategoryCustom categoryCustom = categoryMapperCustom.getCategoryById(status,id);
		return categoryCustom;
	}

    @Override
    public void deleteCategory(Integer id) throws Exception {
        categoryMapperCustom.deleteCategory(id);
    }

    @Override
    public CategoryCustom getCategoryById(Integer status,Integer id) throws Exception {
		CategoryCustom categoryCustom = categoryMapperCustom.getCategoryById(status,id);
        return categoryCustom;
    }

	@Override
	public void insertCategory(Category category) throws Exception {
		categoryMapper.insertSelective(category);
	}

	@Override
	public void updateCategory(Category category) throws Exception {
		categoryMapper.updateByPrimaryKeySelective(category);
	}

	@Override
	public Category getCategoryByName(String name) throws Exception {
		Category category = categoryMapperCustom.getCategoryByName(name);
		return category;
	}


}
