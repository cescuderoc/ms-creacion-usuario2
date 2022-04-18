package com.lab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "TBL_PHONE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelefonoModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PHONE_ID")
	private Long idTelefono;
	
    @Column(name = "USR_ID")
	private Long usuarioId;
	
    @Column(name = "NUMBER_PHONE")
	private String number;
	
    @Column(name = "CITY_CODE")
	private String citycode;
	
    @Column(name = "COUNTRY_CODE")
    private String contrycode;
}
