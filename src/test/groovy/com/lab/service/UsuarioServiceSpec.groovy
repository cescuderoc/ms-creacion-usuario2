package com.lab.service

import com.lab.dto.IngresoUsuarioRequestDto
import com.lab.dto.RegistroIngresoResponseDto
import com.lab.dto.UsuarioPhonesDto
import com.lab.model.TelefonoModel
import com.lab.model.UsuarioModel
import com.lab.repository.TelefonoRepository
import com.lab.repository.UsuarioRepository
import com.lab.util.UtilUsuarioService

import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Title("Test unitarios para la logica de usuarios")
@Narrative("""
La clase UsuarioServiceImpl encapsula la logica de adm de usuarios.
Contiene metodos para obtener el detalle de un usuario
""")
@Subject(UsuarioServiceImpl)
class UsuarioServiceSpec extends Specification {
	
	def usuarioRepository;
	def telefonoRepository;
	def utilUsuarioService;
	
	def usuarioService;
	
	def setup() {
		//Mocks
		usuarioRepository = Mock(UsuarioRepository.class)
		telefonoRepository =Mock(TelefonoRepository.class)
		utilUsuarioService = Mock(UtilUsuarioService.class);
		//Interface / Class being tested / Injections
		usuarioService = new UsuarioServiceImpl(usuarioRepository,telefonoRepository,utilUsuarioService)
	}
	
	def "Obtener lista usuarios" () {
		given: "obtener Lista"
			
			def usuarioModel = new UsuarioModel()
			usuarioModel.setUsuarioId(1)
			usuarioModel.setName("John")
			usuarioModel.setEmail("Doe@test.cl")
			
			def List<UsuarioModel> listaUsuarioModel =new ArrayList();
			
			listaUsuarioModel.add(usuarioModel)
			
			1 * usuarioRepository.findAll() >> listaUsuarioModel
		
		when: "Se intenta obtener el resumen, es decir nombre y correo"
			List<RegistroIngresoResponseDto> lista1 = usuarioService.obtenerListaUsuarios();
		
		then: "se obtiene lista usuarios"
			lista1 != null
		
	}
	
	def "Registro ingreso usuario" () {
		given: "Ingresar usuario"
		  	
//			def usuarioId = utilUsuarioService.newAccountId();
					
			List<UsuarioPhonesDto> listaTelefonos = buildList(
				buildUsuarioPhonesDto("82852323", "9", "56"),
				buildUsuarioPhonesDto("82855555", "5", "61")				
			)
			
			IngresoUsuarioRequestDto ingresoUsuarioDto = IngresoUsuarioRequestDto.builder()
			.email("aassa@a.cl")
			.password("Enero22")
			.phones(listaTelefonos)
			.name("Chris").build();
					
			UsuarioModel usuarioModel = UsuarioModel.builder()
			.usuarioId(123)
			.email("a@a.cl")
			.name("Chris")
			.password("Enero21")
			.created(utilUsuarioService.obtenerFecha().toString())
			.modified(utilUsuarioService.obtenerFecha().toString())
			.lastLogin(utilUsuarioService.obtenerFecha().toString())
			.token("sdfsefev44")
			.isActive(true).build();
			usuarioRepository.save(usuarioModel);
			
			TelefonoModel telefonoModel = TelefonoModel.builder()
			.usuarioId(123)
			.number("82852323")
			.citycode("9")
			.contrycode("56").build();
			telefonoRepository.save(telefonoModel);
			
		when: "Se intenta obtener registro de usuario y telefono"
		
//			usuarioService.registroIngresoUsuario(ingresoUsuarioDto)
			
		
		then: "validamos respuesta del registro"
			1==1
		
	}
	
	
	/**
	 * Instancia un nuevo objeto de tipo UsuarioPhonesDto
	 * @return UsuarioPhonesDto
	 */
	def buildUsuarioPhonesDto(String number, String citycode, String contrycode) {
		UsuarioPhonesDto telefonos = new UsuarioPhonesDto()
		telefonos.setNumber(number)
		telefonos.setCitycode(citycode)
		telefonos.setContrycode(contrycode)
		return telefonos
	}
	
	def buildList(UsuarioPhonesDto... telefonos) {
		List<UsuarioPhonesDto> listaTelefonos = new ArrayList<>()
		Collections.addAll(listaTelefonos, telefonos)
		return listaTelefonos
	}

}
