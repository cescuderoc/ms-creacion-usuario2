package com.lab.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.dto.IngresoUsuarioDto;
import com.lab.dto.RegistroIngresoResponseDto;
import com.lab.dto.ResumenUsuarioDto;
import com.lab.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/usuario")
@RequiredArgsConstructor
public class UsuarioController {
	
    private final UsuarioService usuarioService;

    @GetMapping(path = "/health")
    public String getAccountsBalance() {
        return "service is up";
    }
        
	@GetMapping(path = "/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResumenUsuarioDto obtenerResumenUsuario(
    		@PathVariable("idUsuario") long idUsuario) {
        return usuarioService.obtenerResumenUsuario(idUsuario);
    }    
	
	@GetMapping(path = "/lista", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ResumenUsuarioDto> obtenerListaUsuarios() {
		 List<ResumenUsuarioDto> obtenerListaUsuarios = usuarioService.obtenerListaUsuarios();
	        return obtenerListaUsuarios;
    }    

	@GetMapping("/registrarUsuario")
    public void registrarUsuario() {
		usuarioService.registrarUsuario();
    }
	
	@PostMapping(path = "/ingresarUsuario",
    		consumes = MediaType.APPLICATION_JSON_VALUE, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    public RegistroIngresoResponseDto ingresarUsuario(@RequestHeader Map<String, String> a,
    		@RequestBody IngresoUsuarioDto ingresoUsuarioDto) throws Exception {
        return usuarioService.registroIngresoUsuario(ingresoUsuarioDto);
    }
	
	
}
