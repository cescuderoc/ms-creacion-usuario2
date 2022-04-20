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
import com.lab.exception.ClaveInvalidaException;
import com.lab.exception.EmailException;
import com.lab.service.UsuarioService;
import com.lab.util.UtilUsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UtilUsuarioService utilUsuarioService;

	@GetMapping(path = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RegistroIngresoResponseDto>> obtenerListaUsuarios() {
		try {
			return new ResponseEntity<List<RegistroIngresoResponseDto>>(usuarioService.obtenerListaUsuarios(),
					HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping(path = "/ingresarUsuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RegistroIngresoResponseDto> ingresarUsuario(@RequestHeader Map<String, String> a,
			@RequestBody IngresoUsuarioRequestDto ingresoUsuarioDto) {
		try {
			return new ResponseEntity<RegistroIngresoResponseDto>(
					usuarioService.registroIngresoUsuario(ingresoUsuarioDto), HttpStatus.OK);
		} catch (ClaveInvalidaException e) {
			return new ResponseEntity<RegistroIngresoResponseDto>(utilUsuarioService.generarMensajeError(e),
					HttpStatus.BAD_REQUEST);
		} catch (EmailException e) {
			return new ResponseEntity<RegistroIngresoResponseDto>(utilUsuarioService.generarMensajeError(e),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<RegistroIngresoResponseDto>(utilUsuarioService.generarMensajeError(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
