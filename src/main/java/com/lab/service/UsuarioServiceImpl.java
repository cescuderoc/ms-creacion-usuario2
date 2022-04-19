package com.lab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.dto.IngresoUsuarioRequestDto;
import com.lab.dto.RegistroIngresoResponseDto;
import com.lab.dto.UsuarioPhonesDto;
import com.lab.model.TelefonoModel;
import com.lab.model.UsuarioModel;
import com.lab.repository.TelefonoRepository;
import com.lab.repository.UsuarioRepository;
import com.lab.util.UtilUsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;
	
	@Autowired
	private TelefonoRepository telefonoRepository;

	@Autowired
	private UtilUsuarioService utilUsuarioService;

	@Override
	public List<RegistroIngresoResponseDto> obtenerListaUsuarios() {

		List<UsuarioModel> listaUsuarioModel = usuarioRepository.findAll();
		List<RegistroIngresoResponseDto> listaUsuario = new ArrayList<RegistroIngresoResponseDto>();
		for (UsuarioModel str : listaUsuarioModel) {
			RegistroIngresoResponseDto usuario = new RegistroIngresoResponseDto();
			usuario.setId(str.getUsuarioId());
			usuario.setCreated(str.getCreated());
			usuario.setModified(str.getModified());
			usuario.setLastLogin(str.getLastLogin());
			usuario.setToken(str.getToken());
			listaUsuario.add(usuario);
		}
		return listaUsuario;
	}

	@Override
	public RegistroIngresoResponseDto registroIngresoUsuario(IngresoUsuarioRequestDto ingresoUsuarioDto)
			throws Exception {

		try {

			boolean validaPass = utilUsuarioService.validacionClave(ingresoUsuarioDto.getPassword());
			UsuarioModel existeEmail = usuarioRepository.findByEmail(ingresoUsuarioDto.getEmail());
	        if (!validaPass) {
	        	throw new Exception("Clave no cumple con los requisitos: " + ingresoUsuarioDto.getPassword());
	        }else if(existeEmail!=null) {
	        	throw new Exception("Email existe en la BD: " + ingresoUsuarioDto.getEmail());
	        }
			
			Long usuarioId = utilUsuarioService.newAccountId();

			UsuarioModel usuarioModel = UsuarioModel.builder().usuarioId(usuarioId).email(ingresoUsuarioDto.getEmail())
					.name(ingresoUsuarioDto.getName()).password(ingresoUsuarioDto.getPassword())
					.created(utilUsuarioService.obtenerFecha().toString())
					.modified(utilUsuarioService.obtenerFecha().toString())
					.lastLogin(utilUsuarioService.obtenerFecha().toString()).token("sdfsefev44").isActive(true).build();
			usuarioRepository.save(usuarioModel);

			for (UsuarioPhonesDto str : ingresoUsuarioDto.getPhones()) {
				TelefonoModel telefonoModel = new TelefonoModel();
				telefonoModel = TelefonoModel.builder().usuarioId(usuarioId).number(str.getNumber())
						.citycode(str.getCitycode()).contrycode(str.getContrycode()).build();
				telefonoRepository.save(telefonoModel);
			}

			RegistroIngresoResponseDto detalleRespuestaUsuario = RegistroIngresoResponseDto.builder().id(usuarioId)
					.created(usuarioModel.getCreated()).modified(usuarioModel.getModified())
					.lastLogin(usuarioModel.getLastLogin()).token(usuarioModel.getToken())
					.isactive(usuarioModel.isActive()).build();

			return detalleRespuestaUsuario;
		} catch (javax.persistence.RollbackException e) {
			throw new Exception("Error en la BD: " + e.getMessage());
		}
	}

}
