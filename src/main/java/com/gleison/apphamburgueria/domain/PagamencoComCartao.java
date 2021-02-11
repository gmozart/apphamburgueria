package com.gleison.apphamburgueria.domain;

import com.gleison.apphamburgueria.domain.enums.EstadoPagamento;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PAGAMENTO_CARTAO")
public class PagamencoComCartao extends Pagamento{

    private Integer numeroDeParcelas;

    public PagamencoComCartao(){


    }

    public PagamencoComCartao(Long id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
