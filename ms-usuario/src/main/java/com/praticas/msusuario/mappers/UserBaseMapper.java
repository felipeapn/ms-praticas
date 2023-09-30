package com.praticas.msusuario.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.praticas.msusuario.dto.UserBaseDto;
import com.praticas.msusuario.dto.form.UserBaseForm;
import com.praticas.msusuario.entities.UserBase;

@Component
public class UserBaseMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UserBase getUserBase(UserBaseForm userBaseForm) {	
		
		return this.modelMapper.map(userBaseForm, UserBase.class);
		
	}

	public UserBaseDto getUserBaseDto(UserBase userBase) {
		
		return this.modelMapper.map(userBase, UserBaseDto.class);
	}

}
