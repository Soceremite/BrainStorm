package com.liuyadong.brainstorm.service.impl;

import com.liuyadong.brainstorm.entity.Options;
import com.liuyadong.brainstorm.entity.custom.OptionsCustom;
import com.liuyadong.brainstorm.mapper.OptionsMapper;
import com.liuyadong.brainstorm.mapper.custom.OptionsMapperCustom;
import com.liuyadong.brainstorm.service.OptionsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;


public class OptionsServiceImpl implements OptionsService {

	@Autowired
	private OptionsMapperCustom optionsMapperCustom;

	@Autowired
	private OptionsMapper optionsMapper;

	@Override
	public OptionsCustom getOptions() throws Exception {
		Options options = optionsMapperCustom.getOptions();
		OptionsCustom optionsCustom = new OptionsCustom();
		if(options!=null) {
			BeanUtils.copyProperties(options, optionsCustom);
		}
		return optionsCustom;
	}

	@Override
	public void insertOptions(Options options) throws Exception {
		optionsMapper.insertSelective(options);
	}

	@Override
	public void updateOptions(Options options) throws Exception {
		optionsMapper.updateByPrimaryKeySelective(options);
	}
}
