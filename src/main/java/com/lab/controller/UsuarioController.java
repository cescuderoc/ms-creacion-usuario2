package com.lab.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.dto.IngresoUsuarioRequestDto;
import com.lab.dto.RegistroIngresoResponseDto;
import com.lab.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping(path = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RegistroIngresoResponseDto> obtenerListaUsuarios() {
		List<RegistroIngresoResponseDto> obtenerListaUsuarios = usuarioService.obtenerListaUsuarios();
		return obtenerListaUsuarios;
	}

	@PostMapping(path = "/ingresarUsuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RegistroIngresoResponseDto> ingresarUsuario(@RequestHeader Map<String, String> a,
			@RequestBody IngresoUsuarioRequestDto ingresoUsuarioDto) throws Exception {
		return new ResponseEntity<RegistroIngresoResponseDto>(usuarioService.registroIngresoUsuario(ingresoUsuarioDto),HttpStatus.OK) ;
	}
}
