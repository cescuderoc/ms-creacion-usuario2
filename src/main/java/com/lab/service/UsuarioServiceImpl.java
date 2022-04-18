package com.lab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.dto.IngresoUsuarioDto;
import com.lab.dto.RegistroIngresoResponseDto;
import com.lab.dto.ResumenUsuarioDto;
import com.lab.dto.UsuarioPhonesDto;
import com.lab.model.TelefonoModel;
import com.lab.model.UsuarioModel;
import com.lab.model.UsuarioModel2;
import com.lab.repository.IngresaTelefonoRepository;
import com.lab.repository.IngresaUsuarioRepository;
import com.lab.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private IngresaUsuarioRepository ingresaUsuarioRepository;
	
	@Autowired
	private IngresaTelefonoRepository ingresaTelefonoRepository;
	
    @Autowired
    private UtilUsuarioService utilUsuarioService;
	
	public List<UsuarioModel2> getAccounts() {
		return usuarioRepository.findAll();
	}

	@Override
	public ResumenUsuarioDto obtenerResumenUsuario(long idUsuario) {

		UsuarioModel2 usuario = usuarioRepository.getById(idUsuario);

		ResumenUsuarioDto detalleUsuario = ResumenUsuarioDto.builder()
        		.nombre(usuario.getNombre())
        		.correo(usuario.getCorreo())
            	.build();
		
		return detalleUsuario;
	}
	    	
	public List<ResumenUsuarioDto> obtenerListaUsuarios() {
	    
		List<UsuarioModel2> listaUsuarioModel = usuarioRepository.findAll();		
		List<ResumenUsuarioDto> listaUsuario = new ArrayList<ResumenUsuarioDto>();
		ResumenUsuarioDto usuario = new ResumenUsuarioDto();
		for(UsuarioModel2 str : listaUsuarioModel)
		{
		    usuario.setNombre(str.getNombre());
		    usuario.setCorreo(str.getCorreo());
		    listaUsuario.add(usuario);
		}
		return listaUsuario;
	}

	
	public void registrarUsuario() {
		
        UsuarioModel2 usuarioModel =  UsuarioModel2.builder()
                .usuarioId(utilUsuarioService.newAccountId())
                .nombre("test")
                .correo("a@b.cl")
                .build();
        usuarioRepository.save(usuarioModel);

        UsuarioModel2 usuarioModel2 =  UsuarioModel2.builder()
                .usuarioId(utilUsuarioService.newAccountId())
                .nombre("test2")
                .correo("b@c.cl")
                .build();
        usuarioRepository.save(usuarioModel2);
    }
	
	public RegistroIngresoResponseDto registroIngresoUsuario(IngresoUsuarioDto ingresoUsuarioDto) throws Exception {
		
		String claveValida = ingresoUsuarioDto.getPassword();
		
		boolean validaPass = utilUsuarioService.validacionClave(claveValida);
		
		UsuarioModel existeEmail = ingresaUsuarioRepository.findByEmail(ingresoUsuarioDto.getEmail());
		if (existeEmail == null && validaPass) {
			
			Long usuarioId=utilUsuarioService.newAccountId(); 
			
			UsuarioModel usuarioModel =  UsuarioModel.builder()
	                .usuarioId(usuarioId)
	                .email(ingresoUsuarioDto.getEmail())
	                .name(ingresoUsuarioDto.getName())
	                .password(ingresoUsuarioDto.getPassword())
	                .created(utilUsuarioService.obtenerFecha().toString())
	                .modified(utilUsuarioService.obtenerFecha().toString())
	                .lastLogin(utilUsuarioService.obtenerFecha().toString())
	                .token("sdfsefev44")
	                .isActive(true)
	                .build();
			ingresaUsuarioRepository.save(usuarioModel);
			
			for(UsuarioPhonesDto str : ingresoUsuarioDto.getPhones()){
				TelefonoModel telefonoModel = new TelefonoModel();
				telefonoModel =  TelefonoModel.builder()
		                .usuarioId(usuarioId)
		                .number(str.getNumber())
		                .citycode(str.getCitycode())
		                .contrycode(str.getContrycode())
		                .build();
				ingresaTelefonoRepository.save(telefonoModel);
			}
			
			RegistroIngresoResponseDto detalleRespuestaUsuario = RegistroIngresoResponseDto.builder()
	        		.id(usuarioId)
	        		.created(usuarioModel.getCreated())
	        		.modified(usuarioModel.getModified())
	        		.lastLogin(usuarioModel.getLastLogin())
	        		.token(usuarioModel.getToken())
	        		.isactive(usuarioModel.isActive())
	            	.build();
			
			return detalleRespuestaUsuario;
		}else {
			return null;
		}
		
	}
	


}
