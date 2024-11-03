package com.marcotancredo.cursomc.services;

import com.marcotancredo.cursomc.domain.PagamentoComBoleto;
import com.marcotancredo.cursomc.domain.Pedido;
import com.marcotancredo.cursomc.domain.Produto;
import com.marcotancredo.cursomc.domain.enums.EstadoPagamento;
import com.marcotancredo.cursomc.repositories.ItemPedidoRepository;
import com.marcotancredo.cursomc.repositories.PagamentoRepository;
import com.marcotancredo.cursomc.repositories.PedidoRepository;
import com.marcotancredo.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;
    @Autowired
    private BoletoService boletoService;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private ClienteService clienteService;

    public Pedido find(Long id) {
        Optional<Pedido> retorno = repository.findById(id);

        return retorno.orElseThrow(() -> new ObjectNotFoundException(id, Pedido.class));
    }

    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto pagto) {
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        obj = repository.save(obj);
        pagamentoRepository.save(obj.getPagamento());

        Pedido finalObj = obj;
        obj.getItens().stream().peek(item -> {
            item.setDesconto(0.0);
            Produto produto = produtoService.find(item.getProduto().getId());
            item.setProduto(produto);
            item.setPreco(produto.getPreco());
            item.setPedido(finalObj);
        }).forEach(itemPedidoRepository::save);
        System.out.println(obj);
        return obj;
    }
}
