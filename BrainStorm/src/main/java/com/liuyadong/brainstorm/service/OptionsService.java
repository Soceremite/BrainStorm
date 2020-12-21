package com.liuyadong.brainstorm.service;

import com.liuyadong.brainstorm.entity.Options;
import com.liuyadong.brainstorm.entity.custom.OptionsCustom;



public interface OptionsService {
	//获得基本信息
	public OptionsCustom getOptions() throws Exception;

	//新建基本信息
	public void insertOptions(Options options) throws Exception;

	//更新基本信息
	public void updateOptions(Options options) throws Exception;
}
