package com.gleison.apphamburgueria.domain;

import com.gleison.apphamburgueria.domain.enums.EstadoPagamento;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "PAGAMENTO_BOLETO")
public class PagamentoComBoleto extends Pagamento {

    private Date dataVencimento;

    private Date dataPagamento;

    public PagamentoComBoleto(){

    }

    public PagamentoComBoleto(Long id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento ) {
        super(id, estado, pedido);

        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;


    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
