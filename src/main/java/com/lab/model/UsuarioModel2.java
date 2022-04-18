package com.lab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@Table(name = "USUARIO")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioModel2 {
	
    @Id
    @Column(name = "USUARIO_ID")
	private Long usuarioId;
	
    @Column(name = "USUARIO_NOMBRE")
	private String nombre;
	
    @Column(name = "CORREO")
    private String correo;
}
