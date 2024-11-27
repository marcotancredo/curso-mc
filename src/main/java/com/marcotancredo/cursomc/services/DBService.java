package com.marcotancredo.cursomc.services;

import com.marcotancredo.cursomc.domain.*;
import com.marcotancredo.cursomc.domain.enums.EstadoPagamento;
import com.marcotancredo.cursomc.domain.enums.Perfil;
import com.marcotancredo.cursomc.domain.enums.TipoCliente;
import com.marcotancredo.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private BCryptPasswordEncoder pe;

    public void instantiateTestDatabase() throws ParseException {
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        Categoria cat3 = new Categoria(null, "Cama, mesa e bnanho");
        Categoria cat4 = new Categoria(null, "Eletrônicos");
        Categoria cat5 = new Categoria(null, "Jardinagem");
        Categoria cat6 = new Categoria(null, "Decoração");
        Categoria cat7 = new Categoria(null, "Perfumaria");

        Produto prod1 = new Produto(null, "Computador", 2000.00);
        Produto prod2 = new Produto(null, "Impressora", 800.00);
        Produto prod3 = new Produto(null, "Mouse", 80.00);
        Produto prod4 = new Produto(null, "Mesa de escritório", 300.00);
        Produto prod5 = new Produto(null, "Toalha", 50.00);
        Produto prod6 = new Produto(null, "Colcha", 200.00);
        Produto prod7 = new Produto(null, "TV true color", 1200.00);
        Produto prod8 = new Produto(null, "Roçadeira", 800.00);
        Produto prod9 = new Produto(null, "Abajour", 100.00);
        Produto prod10 = new Produto(null, "Pendente", 180.00);
        Produto prod11 = new Produto(null, "Shampoo", 90.00);

        cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
        cat2.getProdutos().addAll(Arrays.asList(prod2, prod4));
        cat3.getProdutos().addAll(Arrays.asList(prod5, prod6));
        cat4.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3, prod7));
        cat5.getProdutos().add(prod8);
        cat6.getProdutos().addAll(Arrays.asList(prod9, prod10));
        cat7.getProdutos().add(prod11);

        prod1.getCategorias().addAll(Arrays.asList(cat1, cat4));
        prod2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        prod3.getCategorias().addAll(Arrays.asList(cat1, cat4));
        prod4.getCategorias().add(cat2);
        prod5.getCategorias().add(cat3);
        prod6.getCategorias().add(cat3);
        prod7.getCategorias().add(cat4);
        prod8.getCategorias().add(cat5);
        prod9.getCategorias().add(cat6);
        prod10.getCategorias().add(cat6);
        prod11.getCategorias().add(cat7);

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade cid1 = new Cidade(null, "Uberlândia", est1);
        Cidade cid2 = new Cidade(null, "São Paulo", est2);
        Cidade cid3 = new Cidade(null, "Campinhas", est2);

        est1.getCidades().add(cid1);
        est2.getCidades().addAll(Arrays.asList(cid2, cid3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

        Cliente cli1 = new Cliente(null, "Maria Silva", "marcotancredo@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA, pe.encode("123456"));
        cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

        Cliente cli2 = new Cliente(null, "Admin dos santos", "marcotancredo@hotmail.com", "50722632070", TipoCliente.PESSOA_FISICA, pe.encode("123456"));
        cli2.addPerfil(Perfil.ADMIN);
        cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

        Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, cid1);
        Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "3877012", cli1, cid2);
        Endereco end3 = new Endereco(null, "Avenida Floriado", "21061", null, "Centro", "281777012", cli2, cid2);

        cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
        cli2.getEnderecos().add(end3);


        clienteRepository.saveAll(Arrays.asList(cli1, cli2));
        enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);

        PagamentoComCartao pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pag1);

        PagamentoComBoleto pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pag2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));

        ItemPedido itemPed1 = new ItemPedido(ped1, prod1, 0.00, 1, 2000.00);
        ItemPedido itemPed2 = new ItemPedido(ped1, prod3, 0.00, 2, 80.00);
        ItemPedido itemPed3 = new ItemPedido(ped2, prod2, 0.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(itemPed1, itemPed2));
        ped2.getItens().add(itemPed3);

        prod1.getItens().add(itemPed1);
        prod2.getItens().add(itemPed3);
        prod3.getItens().add(itemPed2);

        itemPedidoRepository.saveAll(Arrays.asList(itemPed1, itemPed2, itemPed3));
    }
}
