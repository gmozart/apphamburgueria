package com.gleison.apphamburgueria.domain;

import com.gleison.apphamburgueria.domain.enums.TipoCliente;

import javax.persistence.*;

@Entity
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ID_CLIENTE")
    private Long id;

    @Column(name = "NOME_CLIENTE")
    private String nome;

    @Column(name = "EMAIL_CLIENTE")
    private String email;

    @Column(name = "CPF_CNPJ_CLIENTE")
    private String cpfOUcnpj;

    @Column(name = "TIPO_CLIENTE")
    private TipoCliente tipo;



}
