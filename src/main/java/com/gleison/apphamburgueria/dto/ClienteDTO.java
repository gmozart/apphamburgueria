package com.gleison.apphamburgueria.dto;

import com.gleison.apphamburgueria.domain.Cliente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotBlank(message = "Preenchimento Obrigatório")
    @Length(min =5, max = 120, message = "O Tamanho deve ser ente 5 e 120 caracteres")
    private String nome;

    @NotBlank(message = "Preenchimento Obrigatório")
    @Email(message = "Email Inválido")
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
