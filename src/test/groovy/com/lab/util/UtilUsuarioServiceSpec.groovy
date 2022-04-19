package com.lab.util

import static org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

import com.lab.controller.UsuarioController

import spock.lang.Specification

class UtilUsuarioServiceSpec extends Specification{

	def "valida new idUsuario"() {
		given:
			def utilUsuarioService = new UtilUsuarioService()
		when:
			utilUsuarioService.newAccountId()
		then:
			null != utilUsuarioService.newAccountId()
	}
	
	def "obtiene fecha"() {
		given:
			def utilUsuarioService = new UtilUsuarioService()
		when:
			utilUsuarioService.obtenerFecha()
		then:
			null != utilUsuarioService.obtenerFecha()
	}

	def "validacion clave"() {
		given:
			def utilUsuarioService = new UtilUsuarioService()
		when:
			Boolean resultado = utilUsuarioService.validacionClave("Enero2021")
		then:
			true == resultado
	}

}
