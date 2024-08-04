package com.marcotancredo.cursomc.services;

import com.marcotancredo.cursomc.domain.Cliente;
import com.marcotancredo.cursomc.exceptions.ObjectNotFoundException;
import com.marcotancredo.cursomc.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente find(Long id) {
        Optional<Cliente> retorno = repository.findById(id);

        return retorno.orElseThrow(() -> new ObjectNotFoundException(id, Cliente.class));
    }
}
