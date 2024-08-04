package com.marcotancredo.cursomc.resources;

import com.marcotancredo.cursomc.domain.Pedido;
import com.marcotancredo.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable(value = "id") Long id) {
        Pedido pedido = service.buscar(id);
        return ResponseEntity.ok().body(pedido);
    }
}
