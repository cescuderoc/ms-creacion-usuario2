package com.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistroIngresoResponseDto {

	private Long id;

	private String created;
	
	private String modified;
	
	private String lastLogin;
	
	private String token;
	
	private boolean isactive;
	
}
