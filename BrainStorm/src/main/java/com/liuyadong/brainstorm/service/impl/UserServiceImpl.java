package com.liuyadong.brainstorm.service.impl;

import com.liuyadong.brainstorm.mapper.UserMapper;
import com.liuyadong.brainstorm.mapper.custom.UserMapperCustom;
import com.liuyadong.brainstorm.entity.User;
import com.liuyadong.brainstorm.entity.custom.UserCustom;
import com.liuyadong.brainstorm.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapperCustom userMapperCustom;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<UserCustom> listUser() throws Exception {
		List<UserCustom> userCustomList = userMapperCustom.listUser();
		for(int i=0;i<userCustomList.size();i++) {
			Integer thoughtCount = userMapperCustom.countThoughtByUser(userCustomList.get(i).getUserId());
			userCustomList.get(i).setThoughtCount(thoughtCount);
		}
		return userCustomList;
	}
	
	@Override
	public UserCustom getUserById(Integer id) throws Exception {
		
		User user = userMapper.selectByPrimaryKey(id);
		UserCustom userCustom = new UserCustom();
		BeanUtils.copyProperties(user,userCustom);
		return userCustom;
	}
	
	@Override
	public void updateUser(User user) throws Exception {
		userMapper.updateByPrimaryKeySelective(user);
	}
	
	@Override
	public void deleteUser(Integer id) throws Exception {
		userMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public void insertUser(User user) throws Exception {
		user.setUserRegisterTime(new Date());
		userMapper.insertSelective(user);
	}

	@Override
	public User getUserByNameOrEmail(String str) throws Exception {
		User user = userMapperCustom.getUserByNameOrEmail(str);
		return user;
	}

	@Override
	public User getUserByName(String name) throws Exception {
		User user = userMapperCustom.getUserByName(name);
		return user;
	}

	@Override
	public User getUserByEmail(String email) throws Exception {
		User user = userMapperCustom.getUserByEmail(email);
		return user;
	}


}
