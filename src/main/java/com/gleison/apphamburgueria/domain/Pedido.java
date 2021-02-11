package com.gleison.apphamburgueria.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PEDIDO")
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "ID_PEDIDO")
      private Long id;

      private Date instante;

      @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
      private Pagamento pagamento;

      @ManyToOne
      @JoinColumn(name = "cliente_id")
      private Cliente cliente;

      @ManyToOne
      @JoinColumn(name = "endereco_de_entrega_id")
      private Endereco enderecoDeEntrega;

      @OneToMany(mappedBy = "id.pedido")
      private Set<ItemPedido> itens = new HashSet<>();

    public Pedido() {
    }

    public Pedido(Long id, Date instante, Pagamento pagamento, Cliente cliente, Endereco enderecoDeEntrega) {
        super();
        this.id = id;
        this.instante = instante;
        this.pagamento = pagamento;
        this.cliente = cliente;
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Date getInstante() {

        return instante;
    }

    public void setInstante(Date instante) {

        this.instante = instante;
    }

    public Pagamento getPagamento() {

        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {

        this.pagamento = pagamento;
    }

    public Cliente getCliente() {

        return cliente;
    }

    public void setCliente(Cliente cliente) {

        this.cliente = cliente;
    }

    public Endereco getEnderecoDeEntrega() {

        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {

        this.enderecoDeEntrega = enderecoDeEntrega;
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
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
