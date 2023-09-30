package com.praticas.msusuario.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBaseDto implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String mail;
	private String phoneNumber;
	private String linkedin;
	private String github;
	
}
