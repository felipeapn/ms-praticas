package com.praticas.msusuario.service;

import java.util.List;

import com.praticas.msusuario.dto.UserBaseDto;
import com.praticas.msusuario.dto.form.UserBaseForm;

public interface UserBaseService {
	
		UserBaseDto create(UserBaseForm userBaseForm);

		UserBaseDto update(Long id, UserBaseForm usuarioForm);

		List<UserBaseDto> findAll();

		UserBaseDto findById(Long id); 

}
