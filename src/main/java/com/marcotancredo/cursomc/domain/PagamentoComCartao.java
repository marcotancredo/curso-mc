package com.marcotancredo.cursomc.domain;

import com.marcotancredo.cursomc.domain.enums.EstadoPagamento;
import jakarta.persistence.Entity;

@Entity
public class PagamentoComCartao extends Pagamento {
    private Integer numeroParcelas;

    public PagamentoComCartao() {
    }

    public PagamentoComCartao(Long id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
        super(id, estado, pedido);
        this.numeroParcelas = numeroParcelas;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }
}
