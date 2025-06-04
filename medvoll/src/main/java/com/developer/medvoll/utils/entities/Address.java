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
}
