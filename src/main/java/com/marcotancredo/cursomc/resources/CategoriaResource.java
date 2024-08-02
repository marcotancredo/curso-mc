package com.marcotancredo.cursomc.resources;

import com.marcotancredo.cursomc.domain.Categoria;
import com.marcotancredo.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable(value = "id") Long id) {
        Categoria categoria = service.buscar(id);
        return ResponseEntity.ok().body(categoria);
    }
}
