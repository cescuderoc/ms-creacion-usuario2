package com.lab.service

import com.lab.dto.RegistroIngresoResponseDto


import static org.junit.jupiter.api.Assertions.*

import org.hibernate.type.descriptor.java.DataHelper

import spock.lang.Subject

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import com.lab.model.UsuarioModel
import com.lab.model.UsuarioModel2
import com.lab.repository.TelefonoRepository
import com.lab.repository.UsuarioRepository
import com.lab.util.UtilUsuarioService

import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

@Title("Test unitarios para la logica de usuarios")
@Narrative("""
La clase UsuarioServiceImpl encapsula la logica de adm de usuarios.
Contiene metodos para obtener el detalle de un usuario
""")
@Subject(UsuarioServiceImpl)
class UsuarioServiceSpec extends Specification {
	
	def usuarioRepository;
	def utilUsuarioService;
	
	def usuarioService;
	
	def setup() {
		//Mocks
		usuarioRepository = Mock(UsuarioRepository.class)
		utilUsuarioService = Mock(UtilUsuarioService.class);
		//Interface / Class being tested / Injections
		usuarioService = new UsuarioServiceImpl(usuarioRepository)
	}
	
	def "Registro ingreso responseDto" () {
		given: "ingreso usuario"
			
			def usuarioModel = new UsuarioModel()
			usuarioModel.setUsuarioId(1)
			usuarioModel.setName("John")
			usuarioModel.setEmail("Doe@test.cl")
			
			def List<UsuarioModel> listaUsuarioModel =new ArrayList();
			
			listaUsuarioModel.add(usuarioModel)
			
			1 * usuarioRepository.findAll() >> listaUsuarioModel
		
		when: "Se intenta obtener el resumen, es decir, nombre y correo"
			List<RegistroIngresoResponseDto> lista1 = usuarioService.obtenerListaUsuarios();
		
		then: "se obtiene lista usuarios"
			lista1 != null
		
	}
	
	def "Obtener listado usuario" () {
		given: "listado usuario"
			
			def usuarioModel = new UsuarioModel()
			usuarioModel.setUsuarioId(1)
			usuarioModel.setName("John")
			usuarioModel.setEmail("Doe@test.cl")
			
			def List<UsuarioModel> listaUsuarioModel =new ArrayList();
			
			listaUsuarioModel.add(usuarioModel)
			
			1 * usuarioRepository.findAll() >> listaUsuarioModel
		
		when: "Se intenta obtener el resumen, es decir, nombre y correo"
			List<RegistroIngresoResponseDto> lista1 = usuarioService.obtenerListaUsuarios();
		
		then: "se obtiene lista usuarios"
			lista1 != null
		
	}

}
