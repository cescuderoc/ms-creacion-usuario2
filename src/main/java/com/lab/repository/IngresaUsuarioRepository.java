package com.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.model.UsuarioModel;

public interface IngresaUsuarioRepository extends JpaRepository<UsuarioModel, Long>{
 
	UsuarioModel findByEmail(String email);
	
}
