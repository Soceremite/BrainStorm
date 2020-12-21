package com.liuyadong.brainstorm.mapper;

import com.liuyadong.brainstorm.entity.Thought;

public interface ThoughtMapper {
    int deleteByPrimaryKey(Integer thoughtId);

    int insert(Thought record);

    int insertSelective(Thought record);

    Thought selectByPrimaryKey(Integer thoughtId);

    int updateByPrimaryKeySelective(Thought record);

    int updateByPrimaryKeyWithBLOBs(Thought record);

    int updateByPrimaryKey(Thought record);
}