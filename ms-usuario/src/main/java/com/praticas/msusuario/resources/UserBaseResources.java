package com.praticas.msusuario.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.praticas.msusuario.dto.UserBaseDto;
import com.praticas.msusuario.dto.form.UserBaseForm;
import com.praticas.msusuario.entities.UserBase;
import com.praticas.msusuario.repositories.UserBaseRepository;
import com.praticas.msusuario.service.UserBaseService;

@RestController
@RequestMapping(value = "/userbase")
public class UserBaseResources {
	
	@Autowired
	private UserBaseRepository userBaseRepository;
	
	@Autowired
	private UserBaseService userBaseService;
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserBaseDto> create (@Valid @RequestBody UserBaseForm usuarioForm) {
		
		UserBaseDto userBaseDto = userBaseService.create(usuarioForm);
		
		return ResponseEntity.ok(userBaseDto);
	}
	
	@GetMapping
	public ResponseEntity<List<UserBase>> findAll() {
		List<UserBase> list = userBaseRepository.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserBase> findById(@PathVariable Long id) {
		UserBase userBase = userBaseRepository.findById(id).get();
		return ResponseEntity.ok(userBase);
	}

}
