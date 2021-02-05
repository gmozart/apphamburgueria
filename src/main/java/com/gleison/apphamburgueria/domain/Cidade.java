package com.gleison.apphamburgueria.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="CIDADE")
public class Cidade implements Serializable{
    private static final long serialVersionUID = 1L;
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_CIDADE")
    private Long id;

    @Column(name="NOME_CIDADE")
    private String nome;


    public Cidade() {
    }

    public Cidade(Long id, String nome) {
        this.id = id;
        this.nome = nome;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cidade cidade = (Cidade) o;
        return Objects.equals(id, cidade.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
