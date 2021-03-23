package com.gleison.apphamburgueria.dto;


import com.gleison.apphamburgueria.domain.Categoria;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Preenchimento Obrigatório")
    @Size(min =5, max = 80, message = "O Tamanho deve ser ente 5 e 80 caracteres")
    private String nome;

    public CategoriaDTO(){

    }
    public CategoriaDTO(Categoria obj){

        id = obj.getId();

        nome = obj.getNome();
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }
}
