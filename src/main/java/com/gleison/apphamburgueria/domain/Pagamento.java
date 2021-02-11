package com.gleison.apphamburgueria.domain;

import com.gleison.apphamburgueria.domain.enums.EstadoPagamento;

import javax.persistence.*;

@Entity
@Table(name = "PAGAMENTO")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PAGAMENTO")
    private Long id;

    private EstadoPagamento estado;

    private Pedido pedido;


    public Pagamento(){

    }

    public Pagamento(Long id, EstadoPagamento estado, Pedido pedido) {
        this.id = id;
        this.estado = estado;
        this.pedido = pedido;
    }
}
