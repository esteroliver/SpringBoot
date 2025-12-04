package com.developer.medvoll.utils.entities;

import lombok.Data;

@Data
public class Address {

	private String logradouro;

	private String numero;

	private String bairro;

	private String cep;

	private String cidade;

	private String uf;

	private String complemento;

	public Address() {
	}

	public Address(AddressDto addressDto) {
		this.logradouro = addressDto.logradouro();
		this.numero = addressDto.numero();
		this.complemento = addressDto.complemento();
		this.cep = addressDto.cep();
		this.uf = addressDto.uf();
		this.bairro = addressDto.bairro();
		this.cidade = addressDto.cidade();
	}

}
