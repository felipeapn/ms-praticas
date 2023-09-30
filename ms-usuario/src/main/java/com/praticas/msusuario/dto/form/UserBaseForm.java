package com.praticas.msusuario.dto.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBaseForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@NotNull(message = "No puede ser vacio!")
	private String name;
	
	@NotNull(message = "No puede ser vacio!")
	private String mail;
	
	@NotNull(message = "No puede ser vacio!")
	private String phoneNumber;
	
	private String linkedin;
	
	private String github;

}
