package com.gleison.apphamburgueria.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PEDIDO")
public class Pedido {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "ID_PEDIDO")
      private Long id;

      private Date instante;

    public Pedido() {
    }

    public Pedido(Long id, Date instante) {
        this.id = id;
        this.instante = instante;
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
}
