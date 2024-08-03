package com.marcotancredo.cursomc.resources;

import com.marcotancredo.cursomc.domain.Categoria;
import com.marcotancredo.cursomc.domain.Cliente;
import com.marcotancredo.cursomc.services.CategoriaService;
import com.marcotancredo.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable(value = "id") Long id) {
        Cliente cliente = service.buscar(id);
        return ResponseEntity.ok().body(cliente);
    }
}