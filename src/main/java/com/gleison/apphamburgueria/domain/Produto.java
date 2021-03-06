package com.gleison.apphamburgueria.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "PRODUTO")
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO")
    private Long id;

    @Column(name = "NOME_PRODUTO")
    private String nome;

    @Column(name = "PRECO_PRODUTO")
    private BigDecimal preco;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "PRODUTO_CATEGORIA", joinColumns = @JoinColumn(name = "produto_id"),
    inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.produto")
    private Set<ItemPedido> itens = new HashSet<>();

    @JsonIgnore
    public List<Pedido> getPedidos(){

        List<Pedido> lista = new ArrayList<>();

        for(ItemPedido x : itens){

            lista.add(x.getPedido());

        }
      return lista;
    }

    public Produto(){
    }

    public Produto(Long id, String nome, BigDecimal preco){
        super();

        this.id = id;
        this.nome = nome;
        this.preco = preco;

    }

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

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
