package com.lab.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.lab.dto.RegistroIngresoResponseDto;

@Component
public class UtilUsuarioService {

	public Long newAccountId() {
		return System.nanoTime();
	}

	public LocalDateTime obtenerFecha() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		dtf.format(now);
		return now;
	}

	public boolean validacionClave(String claveValida) {
		Pattern pat = Pattern.compile("^([A-Z]{1})([a-z].*)(\\d{2})$");
		Matcher mat = pat.matcher(claveValida);
		if (mat.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	public RegistroIngresoResponseDto generarMensajeError(Exception e) {
		RegistroIngresoResponseDto response = new RegistroIngresoResponseDto();
		response.setCreated("NO");
		response.setMessage(e.getMessage());
		return response;
	}

}
