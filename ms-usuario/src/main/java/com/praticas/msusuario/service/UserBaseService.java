package com.praticas.msusuario.service;

import com.praticas.msusuario.dto.UserBaseDto;
import com.praticas.msusuario.dto.form.UserBaseForm;

public interface UserBaseService {
	
		UserBaseDto create(UserBaseForm userBaseForm); 

}
