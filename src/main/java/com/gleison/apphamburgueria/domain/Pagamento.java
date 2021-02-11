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
}
