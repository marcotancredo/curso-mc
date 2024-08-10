package com.marcotancredo.cursomc.resources;

import com.marcotancredo.cursomc.domain.Cliente;
import com.marcotancredo.cursomc.dto.ClienteDTO;
import com.marcotancredo.cursomc.dto.ClienteNewDTO;
import com.marcotancredo.cursomc.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> find(@PathVariable(value = "id") Long id) {
        Cliente cliente = service.find(id);
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> insert(@Valid @RequestBody ClienteNewDTO objDTO) {
        Cliente obj = service.fromDTO(objDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ClienteDTO objDTO) {
        Cliente obj = service.fromDTO(objDTO);
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> list = service.findAll();
        List<ClienteDTO> retornoClientes = list.stream().map(ClienteDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(retornoClientes);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                       @RequestParam(value = "lines", required = false, defaultValue = "24") Integer linesPerPage,
                                                       @RequestParam(value = "orderBy", required = false, defaultValue = "nome") String orderBy,
                                                       @RequestParam(value = "direction", required = false, defaultValue = "ASC") String direction) {
        Page<Cliente> listaPaginada = service.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> retornoPaginado = listaPaginada.map(ClienteDTO::new);
        return ResponseEntity.ok().body(retornoPaginado);
    }
}
