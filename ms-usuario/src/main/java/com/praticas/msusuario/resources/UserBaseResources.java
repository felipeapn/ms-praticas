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

@RestController
@RequestMapping(value = "/userbase")
public class UserBaseResources {
		
	@Autowired
	private UserBaseService userBaseService;
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserBaseDto> create (@Valid @RequestBody UserBaseForm usuarioForm) {
		
		UserBaseDto userBaseDto = userBaseService.create(usuarioForm);
		
		userBaseDto.add(linkTo(methodOn(UserBaseResources.class).findAll())
				.withRel("UserList"));
		
		return ResponseEntity.ok(userBaseDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserBaseDto> update (@PathVariable Long id, @RequestBody UserBaseForm usuarioForm) {
		
		UserBaseDto userBaseDto = userBaseService.update(id, usuarioForm);
		
		userBaseDto.add(linkTo(methodOn(UserBaseResources.class).findAll())
				.withRel("UserList"));
		
		return ResponseEntity.ok(userBaseDto);
	}
	
	@GetMapping
	public ResponseEntity<List<UserBaseDto>> findAll() {
		
		List<UserBaseDto> list = userBaseService.findAll();
		
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		list.forEach(user -> user.add(linkTo(methodOn(UserBaseResources.class).findById(user.getId()))
								.withSelfRel()));
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserBaseDto> findById(@PathVariable Long id) {
		
		UserBaseDto userBaseDto = userBaseService.findById(id);
		
		userBaseDto.add(linkTo(methodOn(UserBaseResources.class).findAll())
				.withRel("UserList"));
		
		return ResponseEntity.ok(userBaseDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		
		userBaseService.delete(id);
		
	}
	

}
