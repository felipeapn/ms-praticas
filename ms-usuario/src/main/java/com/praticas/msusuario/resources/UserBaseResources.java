package com.praticas.msusuario.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.praticas.msusuario.dto.UserBaseDto;
import com.praticas.msusuario.dto.form.UserBaseForm;
import com.praticas.msusuario.service.UserBaseService;
import com.praticas.msusuario.util.LogUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/userbase")
public class UserBaseResources {
		
	@Autowired
	private UserBaseService userBaseService;
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserBaseDto> create (@Valid @RequestBody UserBaseForm usuarioForm) {
		
		log.info("method={} step=userBaseCreate data=[email : {}]", 
				LogUtils.getCurrentMethodName(), usuarioForm.getMail());
		
		UserBaseDto userBaseDto = userBaseService.create(usuarioForm);
		
		userBaseDto.add(linkTo(methodOn(UserBaseResources.class).findAll())
				.withRel("UserList"));
		
		log.info("method={} step=userBaseCreate-ok data=[id : {}, email : {}]", 
				LogUtils.getCurrentMethodName(), userBaseDto.getId(), userBaseDto.getMail());
		
		return ResponseEntity.ok(userBaseDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserBaseDto> update (@PathVariable Long id, @RequestBody UserBaseForm usuarioForm) {
		
		log.info("method={} step=userBaseUpdate data=[id : {}, email : {}]", 
				LogUtils.getCurrentMethodName(), id, usuarioForm.getMail());
		
		UserBaseDto userBaseDto = userBaseService.update(id, usuarioForm);
		
		userBaseDto.add(linkTo(methodOn(UserBaseResources.class).findAll())
				.withRel("UserList"));
		
		log.info("method={} step=userBaseUpdate-ok data=[id : {}, email : {}]", 
				LogUtils.getCurrentMethodName(), userBaseDto.getId(), userBaseDto.getMail());
		
		return ResponseEntity.ok(userBaseDto);
	}
	
	@GetMapping
	public ResponseEntity<List<UserBaseDto>> findAll() {
		
		log.info("method={} step=findAllUserBase", LogUtils.getCurrentMethodName());
		
		List<UserBaseDto> list = userBaseService.findAll();
		
		if (list.isEmpty()) {
			log.info("method={} step=findAllUserBase-NOT_FOUND", LogUtils.getCurrentMethodName());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		log.info("method={} step=findAllUserBase-ok", LogUtils.getCurrentMethodName());
		
		list.forEach(user -> user.add(linkTo(methodOn(UserBaseResources.class).findById(user.getId()))
								.withSelfRel()));
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserBaseDto> findById(@PathVariable Long id) {
		
		log.info("method={} step=findUserBaseByID data=[id : {}]", 
				LogUtils.getCurrentMethodName(), id);
		
		UserBaseDto userBaseDto = userBaseService.findById(id);
				
		userBaseDto.add(linkTo(methodOn(UserBaseResources.class).findAll())
				.withRel("UserList"));
		
		log.info("method={} step=findUserBaseByID-ok data=[id : {}]", 
				LogUtils.getCurrentMethodName(), userBaseDto.getId());
		
		return ResponseEntity.ok(userBaseDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		
		log.info("method={} step=deleteUserBase data=[id : {}]", 
				LogUtils.getCurrentMethodName(), id);
		
		userBaseService.delete(id);
		
		log.info("method={} step=deleteUserBase-ok data=[id : {}]", 
				LogUtils.getCurrentMethodName(), id);
		
	}
	

}
