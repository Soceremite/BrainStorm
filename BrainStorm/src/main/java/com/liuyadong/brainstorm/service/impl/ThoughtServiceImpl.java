package com.liuyadong.brainstorm.service.impl;

import com.liuyadong.brainstorm.entity.custom.*;
import com.liuyadong.brainstorm.mapper.ThoughtMapper;
import com.liuyadong.brainstorm.mapper.CategoryMapper;
import com.liuyadong.brainstorm.mapper.TagMapper;
import com.liuyadong.brainstorm.mapper.UserMapper;
import com.liuyadong.brainstorm.mapper.custom.ThoughtMapperCustom;
import com.liuyadong.brainstorm.mapper.custom.CategoryMapperCustom;
import com.liuyadong.brainstorm.mapper.custom.CommentMapperCustom;
import com.liuyadong.brainstorm.entity.Thought;
import com.liuyadong.brainstorm.entity.Tag;
import com.liuyadong.brainstorm.entity.User;
import com.liuyadong.brainstorm.service.ThoughtService;
import com.liuyadong.brainstorm.util.Functions;
import com.liuyadong.brainstorm.util.others.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Service
public class ThoughtServiceImpl implements ThoughtService {
	@Autowired
	private ThoughtMapperCustom thoughtMapperCustom;
	
	@Autowired
	private ThoughtMapper thoughtMapper;
	
	@Autowired
	private CategoryMapperCustom categoryMapperCustom;

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private TagMapper tagMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CommentMapperCustom commentMapperCustom;


	@Override
	public Integer countThought(Integer status) throws Exception {
		Integer thoughtCount = thoughtMapperCustom.countThought(status);
		return thoughtCount ;
	}
	
	@Override
	public Integer countThoughtComment(Integer status) throws Exception {
		Integer commentCount = thoughtMapperCustom.countThoughtComment(status);
		return commentCount;
	}
	
	
	@Override
	public Integer countThoughtView(Integer status) throws Exception {
		Integer viewCount = thoughtMapperCustom.countThoughtView(status);
		return viewCount;
	}

	@Override
	public List<ThoughtListVo> listThought(Integer status) throws Exception {
		List<ThoughtListVo> thoughtListVoList = new ArrayList<ThoughtListVo>();

		//获得想法列表信息和分页信息
		List<ThoughtCustom> thoughtCustomList = thoughtMapperCustom.listThought(status);

		//获得分类信息
		for (int i = 0; i < thoughtCustomList.size(); i++) {
			ThoughtListVo thoughtListVo = new ThoughtListVo();

			//1、将想法信息装到 thoughtListVoList 中
			ThoughtCustom thoughtCustom = thoughtCustomList.get(i);
			thoughtListVo.setThoughtCustom(thoughtCustom);

			//2、将分类信息装到 thoughtListVoList 中
			List<CategoryCustom> categoryCustomList = new ArrayList<CategoryCustom>();
			Integer parentCategoryId = thoughtCustomList.get(i).getThoughtParentCategoryId();
			Integer childCategoryId = thoughtCustomList.get(i).getThoughtChildCategoryId();
			CategoryCustom categoryCustom = categoryMapperCustom.getCategoryById(1,parentCategoryId);
			CategoryCustom categoryCustom2 = categoryMapperCustom.getCategoryById(1,childCategoryId);
			if (categoryCustom != null) {
				categoryCustomList.add(categoryCustom);
			}
			if (categoryCustom2 != null) {
				categoryCustomList.add(categoryCustom2);
			}
			thoughtListVo.setCategoryCustomList(categoryCustomList);

			//3、获得标签信息
			List<TagCustom> tagCustomList = new ArrayList<TagCustom>();
			String tagIds = thoughtCustomList.get(i).getThoughtTagIds();
			//防止该想法没有分类，空指针
			if (tagIds != null && tagIds != "") {
				String[] tagId = tagIds.split(",");
				for (int j = 0; j < tagId.length; j++) {
					Tag tag = tagMapper.selectByPrimaryKey(Integer.valueOf(tagId[j]));
					//防止标签不存在，被删除
					if (tag != null) {
						TagCustom tagCustom = new TagCustom();
						BeanUtils.copyProperties(tag, tagCustom);
						tagCustomList.add(tagCustom);
					}
				}
			}
			thoughtListVo.setTagCustomList(tagCustomList);

			//4、获得作者信息
			User user = userMapper.selectByPrimaryKey(thoughtCustom.getThoughtUserId());
			UserCustom userCustom = new UserCustom();
			BeanUtils.copyProperties(user, userCustom);
			thoughtListVo.setUserCustom(userCustom);


			thoughtListVoList.add(thoughtListVo);

		}
		return thoughtListVoList;
	}


	@Override
	public ThoughtCustom getThoughtById(Integer status,Integer id) throws Exception {
		return thoughtMapperCustom.getThoughtById(status,id);
	}
	
	@Override
	public void updateThought(Integer id,Thought thought) throws Exception {
		//添加业务校验，通常在service接口对关键
		thought.setThoughtId(id);
		thoughtMapper.updateByPrimaryKeySelective(thought);
	}
	
	@Override
	public void deleteThoughtBatch(Integer[] ids) throws Exception {
		for (int i=0;i<ids.length;i++) {
			thoughtMapper.deleteByPrimaryKey(ids[i]);
		}
	}

	@Override
	public void deleteThought(Integer id) throws Exception {
		thoughtMapper.deleteByPrimaryKey(id);
	}

	
	//分页显示想法列表
	@Override
	public List<ThoughtListVo> listThoughtByPage(Integer status,Integer pageNow,Integer pageSize) throws Exception {
		List<ThoughtListVo> thoughtListVoList = new ArrayList<ThoughtListVo>();
		
		//获得想法列表信息和分页信息
		List<ThoughtCustom> thoughtCustomList = new ArrayList<ThoughtCustom>();
		Page page = null;
		int totalCount = thoughtMapperCustom.countThought(status);
		if (pageNow != null) {
			page = new Page(totalCount, pageNow,pageSize);
			thoughtCustomList = thoughtMapperCustom.listThoughtByPage(status,page.getStartPos(),pageSize);
		} else {
			page = new Page(totalCount, 1,pageSize);
			thoughtCustomList = thoughtMapperCustom.listThoughtByPage(status,page.getStartPos(), pageSize);
		}
		
		//获得分类信息
		for(int i=0;i<thoughtCustomList.size();i++) {
			ThoughtListVo thoughtListVo = new ThoughtListVo();
			
			//1、将想法信息装到 thoughtListVoList 中
			ThoughtCustom thoughtCustom = thoughtCustomList.get(i);
			thoughtListVo.setThoughtCustom(thoughtCustom);
			
			//2、将分类信息装到 thoughtListVoList 中
			List<CategoryCustom> categoryCustomList = new ArrayList<CategoryCustom>();
			Integer parentCategoryId =thoughtCustomList.get(i).getThoughtParentCategoryId();
            Integer childCategoryId =thoughtCustomList.get(i).getThoughtChildCategoryId();
            CategoryCustom categoryCustom = categoryMapperCustom.getCategoryById(status,parentCategoryId);
			CategoryCustom categoryCustom2 = categoryMapperCustom.getCategoryById(status,childCategoryId);
			if(categoryCustom!=null) {
                categoryCustomList.add(categoryCustom);
            }
            if(categoryCustom2!=null) {
                categoryCustomList.add(categoryCustom2);
            }
            thoughtListVo.setCategoryCustomList(categoryCustomList);

			//3、获得标签信息
			List<TagCustom> tagCustomList = new ArrayList<TagCustom>();
			String tagIds = thoughtCustomList.get(i).getThoughtTagIds();
			//防止该想法没有分类，空指针
			if(tagIds!=null && tagIds!="") {
				String[] tagId = tagIds.split(",");
				for (int j = 0; j < tagId.length; j++) {
					Tag tag = tagMapper.selectByPrimaryKey(Integer.valueOf(tagId[j]));
					//防止标签不存在，被删除
					if (tag != null) {
						TagCustom tagCustom = new TagCustom();
						BeanUtils.copyProperties(tag, tagCustom);
						tagCustomList.add(tagCustom);
					}
				}
			}
			thoughtListVo.setTagCustomList(tagCustomList);

			//4、获得作者信息
			User user = userMapper.selectByPrimaryKey(thoughtCustom.getThoughtUserId());
			UserCustom  userCustom = new UserCustom();
			BeanUtils.copyProperties(user,userCustom);
			thoughtListVo.setUserCustom(userCustom);


			thoughtListVoList.add(thoughtListVo);
		}

		if(thoughtListVoList.size()>0) {
			//4、将Page信息存储在第一个元素中
			thoughtListVoList.get(0).setPage(page);
		}
		return thoughtListVoList;
	}
	
	//想法详情页面显示
	@Override
	public ThoughtDetailVo getThoughtDetailById(Integer id) throws Exception {
		ThoughtDetailVo thoughtDetailVo = new ThoughtDetailVo();
		
		//1、获得想法信息
		ThoughtCustom thoughtCustom = thoughtMapperCustom.getThoughtById(1,id);
		if(thoughtCustom ==null) {
			return null;
		}
		thoughtDetailVo.setThoughtCustom(thoughtCustom);
		

		//2、将分类信息装到 thoughtListVoList 中
		List<CategoryCustom> categoryCustomList = new ArrayList<CategoryCustom>();
		Integer cate =thoughtCustom.getThoughtParentCategoryId();
		Integer cate2 =thoughtCustom.getThoughtChildCategoryId();
		CategoryCustom categoryCustom = categoryMapperCustom.getCategoryById(1,cate);
		CategoryCustom categoryCustom2 = categoryMapperCustom.getCategoryById(1,cate2);
		if(categoryCustom!=null) {
            categoryCustomList.add(categoryCustom);
        }
        if(categoryCustom2!=null) {
            categoryCustomList.add(categoryCustom2);
        }
		thoughtDetailVo.setCategoryCustomList(categoryCustomList);
		
		//3、获得想法的标签
		String tag_ids = thoughtCustom.getThoughtTagIds();
		List<TagCustom> tagCustomList = new ArrayList<TagCustom>();
		if (tag_ids != null && tag_ids != "") {
			String[] tags = tag_ids.split(",");
			int tagLength = tags.length;
			
			for (int i = 0; i < tagLength; i++) {
				Tag tag= tagMapper.selectByPrimaryKey(Integer.valueOf(tags[i]));
				if(tag!=null) {
					TagCustom tagCustom = new TagCustom();
					BeanUtils.copyProperties(tag,tagCustom);
					tagCustomList.add(tagCustom);
				}
			}
		}
		thoughtDetailVo.setTagCustomList(tagCustomList);
		
		//4、获得想法的作者
		Integer userId = thoughtCustom.getThoughtUserId();
		User user = userMapper.selectByPrimaryKey(userId);
		UserCustom userCustom = new UserCustom();
		BeanUtils.copyProperties(user,userCustom);
		thoughtDetailVo.setUserCustom(userCustom);
		
		//5、获取评论信息列表
		List<CommentCustom> commentCustomList = commentMapperCustom.listCommentByThoughtId(1,id);
		//给每个评论用户添加头像
		for(int i=0;i<commentCustomList.size();i++) {
			String avatar = Functions.getGravatar(commentCustomList.get(i).getCommentAuthorEmail());
			commentCustomList.get(i).setCommentAuthorAvatar(avatar);
		}
		thoughtDetailVo.setCommentCustomList(commentCustomList);


		return thoughtDetailVo;
	}
	

	//想法查询结果分页
	@Override
	public List<ThoughtSearchVo> listSearchResultByPage(Integer status,HttpServletRequest request, Model model,Integer pageNow,Integer pageSize,String query) throws Exception {
		Page page = null;
		List<ThoughtCustom> thoughtCustomList = new ArrayList<ThoughtCustom>();
		int totalCount = thoughtMapperCustom.getSearchResultCount(status,query);


        if (pageNow != null) {
            page = new Page(totalCount, pageNow, pageSize);
            thoughtCustomList = this.thoughtMapperCustom.listSearchResultByPage(status,query, page.getStartPos(), page.getPageSize());
        } else {
            page = new Page(totalCount, 1, pageSize);
            thoughtCustomList = this.thoughtMapperCustom.listSearchResultByPage(status,query, page.getStartPos(), page.getPageSize());
        }

        List<ThoughtSearchVo> thoughtSearchVoList = new ArrayList<ThoughtSearchVo>();

        //查询结果条数为0，下面的不执行，防止空指针
		if(totalCount!=0) {
            for (int i = 0; i < thoughtCustomList.size(); i++) {
                ThoughtSearchVo thoughtSearchVo = new ThoughtSearchVo();

                //1、将想法信息装到 thoughtListVoList 中
                ThoughtCustom thoughtCustom = thoughtCustomList.get(i);
                thoughtSearchVo.setThoughtCustom(thoughtCustom);

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
                thoughtSearchVo.setCategoryCustomList(categoryCustomList);

                //3、获得标签信息
                List<TagCustom> tagCustomList = new ArrayList<TagCustom>();
                String tagIds = thoughtCustomList.get(i).getThoughtTagIds();
                if(tagIds!=null &&tagIds!="") {
                    String[] tagId = tagIds.split(",");
                    for (int j = 0; j < tagId.length; j++) {
                        Tag tag = tagMapper.selectByPrimaryKey(Integer.valueOf(tagId[j]));
                        if (tag != null) {
                            TagCustom tagCustom = new TagCustom();
                            BeanUtils.copyProperties(tag, tagCustom);
                            tagCustomList.add(tagCustom);
                        }
                    }
                }
                thoughtSearchVo.setTagCustomList(tagCustomList);

                //4、获得作者信息
                User user = userMapper.selectByPrimaryKey(thoughtCustom.getThoughtUserId());
                UserCustom userCustom = new UserCustom();
                BeanUtils.copyProperties(user, userCustom);
                thoughtSearchVo.setUserCustom(userCustom);


                thoughtSearchVoList.add(thoughtSearchVo);
            }
        } else {
		    //不执行的话，也要创建一个元素，存储分页信息和查询关键字
            ThoughtSearchVo thoughtSearchVo = new ThoughtSearchVo();
            thoughtSearchVoList.add(thoughtSearchVo);
        }
		//5、page信息存储在第一个元素中
		thoughtSearchVoList.get(0).setPage(page);

		//6、将查询的关键词存储到第一个元素
		thoughtSearchVoList.get(0).setQuery(query);


		return thoughtSearchVoList;

	}
	
	//相似想法获取
	@Override
	public List<ThoughtCustom> listThoughtWithSameCategory(Integer status,Integer parentCategoryId,Integer childCategoryId, Integer limit) throws Exception {
		List<ThoughtCustom> similarThoughtList = thoughtMapperCustom.listThoughtWithSameCategory(status,parentCategoryId,childCategoryId,limit);
		return similarThoughtList;
	}
	
	
	//访问量从多到少的想法获取
	@Override
	public List<ThoughtCustom> listThoughtByViewCount(Integer status,Integer limit) throws Exception {
		List<ThoughtCustom> mostViewThoughtList = thoughtMapperCustom.listThoughtByViewCount(status,limit);
		return mostViewThoughtList;
	}
	
	//获取下一篇想法
	@Override
	public ThoughtCustom getAfterThought(Integer status,Integer id) throws Exception {
		ThoughtCustom thoughtCustom = thoughtMapperCustom.getAfterThought(status,id);
		return thoughtCustom;
	}
	
	//获取上一篇想法
	@Override
	public ThoughtCustom getPreThought(Integer status,Integer id) throws Exception {
		ThoughtCustom thoughtCustom = thoughtMapperCustom.getPreThought(status,id);
		return thoughtCustom;
	}
	
	//获得随机想法
	@Override
	public List<ThoughtCustom> listRandomThought(Integer status,Integer limit) throws Exception {
		List<ThoughtCustom> thoughtCustomsList = thoughtMapperCustom.listRandomThought(status,limit);
		return thoughtCustomsList;
	}
	
	//获得热评想法列表
	@Override
	public List<ThoughtCustom> listThoughtByCommentCount(Integer status,Integer limit) throws Exception {
		List<ThoughtCustom> thoughtCustomsList = thoughtMapperCustom.listThoughtByCommentCount(status,limit);
		return thoughtCustomsList;
	}


    //添加想法
    @Override
    public void insertThought(Thought thought) throws Exception {
        thoughtMapper.insertSelective(thought);
    }

    //统计某个分类的想法数
	@Override
	public Integer countThoughtWithCategory(Integer status,Integer id) throws Exception {
		int count = thoughtMapperCustom.countThoughtByCategory(status,id);
		return count;
	}

	//统计某个标签的想法数
	@Override
	public Integer countThoughtWithTag(Integer status,Integer id) throws Exception {
		int count = thoughtMapperCustom.countThoughtByTag(status,id);
		return count;
	}

	@Override
	public void updateCommentCount(Integer thoughtId) throws Exception {
		thoughtMapperCustom.updateCommentCount(thoughtId);
	}

	@Override
	public ThoughtCustom getLastUpdateThought() throws Exception {
		ThoughtCustom thoughtCustom = thoughtMapperCustom.getLastUpdateThought();
		return thoughtCustom;
	}


}
