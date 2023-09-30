package com.praticas.msusuario.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praticas.msusuario.dto.UserBaseDto;
import com.praticas.msusuario.dto.form.UserBaseForm;
import com.praticas.msusuario.entities.UserBase;
import com.praticas.msusuario.mappers.UserBaseMapper;
import com.praticas.msusuario.repositories.UserBaseRepository;
import com.praticas.msusuario.service.UserBaseService;

@Service
public class UserBaseServiceImpl implements UserBaseService {

	@Autowired
	private UserBaseRepository userBaseRepository;
	
	@Autowired
	private UserBaseMapper userBaseMapper;
	
	@Override
	public UserBaseDto create(UserBaseForm userBaseForm) {
		
		UserBase userBase = this.userBaseMapper.getUserBase(userBaseForm);
		
		this.userBaseRepository.save(userBase);
		
		return this.userBaseMapper.getUserBaseDto(this.userBaseRepository.save(userBase));
	}

}
