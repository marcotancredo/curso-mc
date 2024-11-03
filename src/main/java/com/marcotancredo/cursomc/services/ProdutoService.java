package com.marcotancredo.cursomc.services;

import com.marcotancredo.cursomc.domain.Categoria;
import com.marcotancredo.cursomc.domain.Produto;
import com.marcotancredo.cursomc.repositories.CategoriaRepository;
import com.marcotancredo.cursomc.repositories.ProdutoRepository;
import com.marcotancredo.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Long id) {
        Optional<Produto> retorno = repository.findById(id);

        return retorno.orElseThrow(() -> new ObjectNotFoundException(id, Produto.class));
    }

    public Page<Produto> search(String nome, List<Long> categoriaIds,
                                Integer page, Integer linesPerPage,
                                String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(categoriaIds);

        return repository.search(nome, categorias, pageRequest);
    }
}
