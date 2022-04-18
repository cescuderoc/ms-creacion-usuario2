package com.lab.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngresoUsuarioDto {
	
	private String email;
    
	private String password;
	
	private List<UsuarioPhonesDto> phones;

	private String name;
}
