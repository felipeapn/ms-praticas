package com.praticas.msusuario.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.praticas.msusuario.repositories.UserBaseRepository;
import com.praticas.msusuario.entities.UserBase;

@RestController
@RequestMapping(value = "/userbase")
public class UserBaseResources {
	
	@Autowired
	private UserBaseRepository userBaseRepository;
	
	@GetMapping
	public ResponseEntity<List<UserBase>> findAll() {
		List<UserBase> list = userBaseRepository.findAll();
		return ResponseEntity.ok(list);
	}

}
