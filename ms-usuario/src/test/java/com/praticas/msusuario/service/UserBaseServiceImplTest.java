package com.praticas.msusuario.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;

import com.praticas.msusuario.configuration.MapperConfig;
import com.praticas.msusuario.dto.UserBaseDto;
import com.praticas.msusuario.dto.form.UserBaseForm;
import com.praticas.msusuario.entities.UserBase;
import com.praticas.msusuario.exception.EmailAlreadyTakenException;
import com.praticas.msusuario.mappers.UserBaseMapper;
import com.praticas.msusuario.repositories.UserBaseRepository;
import com.praticas.msusuario.service.impl.UserBaseServiceImpl;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes= {MapperConfig.class})
class UserBaseServiceImplTest {
	
	@Mock
	private UserBaseRepository userBaseRepository;
	
	@Mock
	private UserBaseMapper userBaseMapper;
	
	@Spy
	private ModelMapper modelMapper;
	
	@InjectMocks
	private UserBaseServiceImpl underTest;

	private static UserBaseForm userBaseForm;
	private static UserBaseDto userBaseDto;
	private static UserBase userBase;
		
	@BeforeEach
	public void init() {
		userBaseForm = new UserBaseForm();
		userBaseForm.setName("Nome Form");
		userBaseForm.setMail("formemail@mail.com");
		
		userBaseDto = new UserBaseDto();
		userBaseDto.setId(1l);
		userBaseDto.setName("Nome Form");
		userBaseDto.setMail("formemail@mail.com");
		
		userBase = new UserBase();
		userBase.setId(1l);
		userBase.setName("Nome Form");
		userBase.setMail("formemail@mail.com");
	}
	
	@AfterEach
	public void setNull() {
		userBaseForm = null;
		userBaseForm = null;
		userBaseForm = null;
	}
	
	@Test
	void canCreate() {
		//given
		
		//when
		when(userBaseMapper.getUserBase(any(UserBaseForm.class))).thenReturn(userBase);
		when(userBaseRepository.save(userBase)).thenReturn(userBase);
		when(userBaseMapper.getUserBaseDto(userBase)).thenReturn(userBaseDto);
		
		UserBaseDto userBaseDtoReturned = underTest.create(userBaseForm);
		
		//then
		verify(userBaseMapper, times(1)).getUserBase(any());
		verify(userBaseRepository, times(1)).save(any());
		verify(userBaseMapper, times(1)).getUserBaseDto(any());
				
		assertEquals(userBaseForm.getMail(), userBaseDtoReturned.getMail());
	}
	
	@Test
	void canNotCreateEmailAlreadyTaken() {
		//given
		
		//when
		
		when(userBaseRepository.findByMail(any(String.class))).thenReturn(Optional.of(userBase));
		
		EmailAlreadyTakenException emailAlreadyTakenException = assertThrows(EmailAlreadyTakenException.class, 
				() -> underTest.create(userBaseForm));
				
		//then
		assertEquals("Email ya utilizado", emailAlreadyTakenException.getDescription());
		
		verify(userBaseMapper, times(0)).getUserBase(any());
		verify(userBaseRepository, times(0)).save(any());
		verify(userBaseMapper, times(0)).getUserBaseDto(any());
		
	}

	@Test
	void canUpdate() {
		//given
		
		//when
		when(userBaseRepository.findById(anyLong())).thenReturn(Optional.of(userBase));
		when(userBaseRepository.save(userBase)).thenReturn(userBase);
		when(userBaseMapper.getUserBaseDto(userBase)).thenReturn(userBaseDto);
		
		UserBaseDto userBaseDtoReturned = underTest.update(anyLong(), userBaseForm);
		
		//then
		verify(userBaseRepository, times(1)).findById(anyLong());
		verify(userBaseRepository, times(1)).save(any());
		verify(userBaseMapper, times(1)).getUserBaseDto(any());
				
		assertEquals(userBaseForm.getMail(), userBaseDtoReturned.getMail());
	}
	
	@Test
	void canNotFindByIdOnUpdate() {
		//given
		
		//when
		when(userBaseRepository.findById(anyLong())).thenReturn(Optional.empty());
						
		//then
		
		EmptyResultDataAccessException emptyResultDataAccessException = assertThrows(EmptyResultDataAccessException.class, 
				() -> underTest.update(anyLong(), userBaseForm));
		
	
		assertEquals("Usario de ID no esta registrado", emptyResultDataAccessException.getMessage());
		verify(userBaseRepository, times(0)).save(any(UserBase.class));
	}

	@Test
	void canFindAll() {
		//given				
		
		//when
		when(userBaseRepository.findAll()).thenReturn(List.of(userBase));
		when(userBaseMapper.getUserBaseDto(userBase)).thenReturn(userBaseDto);
		
		List<UserBaseDto> userBaseDtosReturned = underTest.findAll();
		
		//then
		verify(userBaseRepository, times(1)).findAll();
				
		assertEquals(1, userBaseDtosReturned.size());
		
		assertEquals(userBase.getId(), userBaseDtosReturned.get(0).getId());
		
	}

	@Test
	void canFindById() {
		//given
		
		//when
		when(userBaseRepository.findById(anyLong())).thenReturn(Optional.of(userBase));
		when(userBaseMapper.getUserBaseDto(userBase)).thenReturn(userBaseDto);
		
		UserBaseDto userBaseDtoReturned = underTest.findById(anyLong());
		
		//then
		verify(userBaseRepository, times(1)).findById(anyLong());
		verify(userBaseMapper, times(1)).getUserBaseDto(any());
				
		assertEquals(userBase.getMail(), userBaseDtoReturned.getMail());
	}
	
	@Test
	void canNotFindById() {
		//given
		
		Optional.empty();
		
		//when
		when(userBaseRepository.findById(anyLong())).thenReturn(Optional.empty());
						
		//then
		
		EmptyResultDataAccessException emptyResultDataAccessException = assertThrows(EmptyResultDataAccessException.class, 
				() -> underTest.findById(anyLong()));
		
	
		assertEquals("Usario de ID no esta registrado", emptyResultDataAccessException.getMessage());
		
	}

	@Test
	void canDelete() {
		//when
		doNothing().when(userBaseRepository).deleteById(anyLong());
				
		underTest.delete(anyLong());
		
		//then
		verify(userBaseRepository, times(1)).deleteById(anyLong());

	}

}
