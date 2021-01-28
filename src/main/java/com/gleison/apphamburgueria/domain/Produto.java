package com.gleison.apphamburgueria.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO")
    private Long id;

    @Column(name = "NOME_PRODUTO")
    private String nome;

    @Column(name = "PRECO_PRODUTO")
    private BigDecimal preco;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Produto(){


    }

    public Produto(Long id, String nome, BigDecimal preco){
      super();

      this.id = id;
      this.nome = nome;
      this.preco = preco;

    }



}
