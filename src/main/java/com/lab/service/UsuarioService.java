package com.lab.service;

import java.util.List;

import com.lab.dto.IngresoUsuarioDto;
import com.lab.dto.RegistroIngresoResponseDto;
import com.lab.dto.ResumenUsuarioDto;

public interface UsuarioService {
	
	ResumenUsuarioDto obtenerResumenUsuario(long idUsuario);
	   
    public List<ResumenUsuarioDto> obtenerListaUsuarios();
    
    public void registrarUsuario();
    
    RegistroIngresoResponseDto registroIngresoUsuario(IngresoUsuarioDto ingresoUsuarioDto) throws Exception;
}
