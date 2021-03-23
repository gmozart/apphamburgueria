package com.gleison.apphamburgueria.dto;

import com.gleison.apphamburgueria.domain.Cliente;

import java.io.Serializable;

public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String email;


    public ClienteDTO(Cliente objCliente){

        id = objCliente.getId();
        nome = objCliente.getNome();
        email = objCliente.getEmail();


    }

    public ClienteDTO(){

     }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
