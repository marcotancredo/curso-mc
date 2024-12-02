package com.marcotancredo.cursomc.resources;

import com.marcotancredo.cursomc.domain.Pedido;
import com.marcotancredo.cursomc.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> find(@PathVariable(value = "id") Long id) {
        Pedido pedido = service.find(id);
        return ResponseEntity.ok().body(pedido);
    }

    @PostMapping
    public ResponseEntity<Pedido> insert(@Valid @RequestBody Pedido obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @GetMapping
    public ResponseEntity<Page<Pedido>> findPage(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                       @RequestParam(value = "lines", required = false, defaultValue = "24") Integer linesPerPage,
                                                       @RequestParam(value = "orderBy", required = false, defaultValue = "instante") String orderBy,
                                                       @RequestParam(value = "direction", required = false, defaultValue = "DESC") String direction) {
        Page<Pedido> listaPaginada = service.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(listaPaginada);
    }
}
