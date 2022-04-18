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
@Table(name = "TBL_USER")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioModel {
	
    @Id
    @Column(name = "USR_ID")
	private Long usuarioId;
	
    @Column(name = "USR_EMAIL")
    private String email;
    
    @Column(name = "USR_PASSWORD")
	private String password;
	
    @Column(name = "USR_NAME")
	private String name;
    
    @Column(name = "DATE_CREATED")
	private String created;
	
    @Column(name = "DATE_MODIFIED")
	private String modified;
    
    @Column(name = "DATE_LAST_LOGIN")
	private String lastLogin;
    
    @Column(name = "TOKEN")
	private String token;

    @Column(name = "ISACTIVE")
	private boolean isActive;
}
