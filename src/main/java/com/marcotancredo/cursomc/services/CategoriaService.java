package com.marcotancredo.cursomc.services;

import com.marcotancredo.cursomc.domain.Categoria;
import com.marcotancredo.cursomc.repositories.CategoriaRepository;
import com.marcotancredo.cursomc.services.exceptions.DataIntegrityException;
import com.marcotancredo.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public void update(Categoria obj) {
        find(obj.getId());
        insert(obj);
    }

    public void delete(Long id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }

    }
}
