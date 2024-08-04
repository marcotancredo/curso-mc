package com.marcotancredo.cursomc.services;

import com.marcotancredo.cursomc.domain.Categoria;
import com.marcotancredo.cursomc.exceptions.ObjectNotFoundException;
import com.marcotancredo.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria find(Long id) {
        Optional<Categoria> retorno = repository.findById(id);

        return retorno.orElseThrow(() -> new ObjectNotFoundException(id, Categoria.class));
    }

    public Categoria insert(Categoria obj) {
        return repository.save(obj);
    }

    public Categoria update(Categoria obj) {
        find(obj.getId());
        return insert(obj);
    }
}
