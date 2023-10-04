package com.praticas.msusuario.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.praticas.msusuario.entities.UserBase;

public interface UserBaseRepository extends JpaRepository<UserBase, Long> {
	
	Optional<UserBase> findByMail(String mail);

}
