package com.marcotancredo.cursomc.services;

import com.marcotancredo.cursomc.domain.Categoria;
import com.marcotancredo.cursomc.repositories.CategoriaRepository;
import com.marcotancredo.cursomc.services.exceptions.DataIntegrityException;
import com.marcotancredo.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }
}
