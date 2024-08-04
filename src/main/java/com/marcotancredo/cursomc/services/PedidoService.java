package com.marcotancredo.cursomc.services;

import com.marcotancredo.cursomc.domain.Pedido;
import com.marcotancredo.cursomc.exceptions.ObjectNotFoundException;
import com.marcotancredo.cursomc.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public Pedido find(Long id) {
        Optional<Pedido> retorno = repository.findById(id);

        return retorno.orElseThrow(() -> new ObjectNotFoundException(id, Pedido.class));
    }
}
