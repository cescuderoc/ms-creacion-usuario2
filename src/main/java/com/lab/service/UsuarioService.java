package com.lab.service;

import java.util.List;

import com.lab.dto.IngresoUsuarioRequestDto;
import com.lab.dto.RegistroIngresoResponseDto;

public interface UsuarioService {

	List<RegistroIngresoResponseDto> obtenerListaUsuarios() throws Exception;

	RegistroIngresoResponseDto registroIngresoUsuario(IngresoUsuarioRequestDto ingresoUsuarioDto) throws Exception;
}
