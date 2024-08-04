package com.marcotancredo.cursomc.resources;

import com.marcotancredo.cursomc.domain.Categoria;
import com.marcotancredo.cursomc.dto.CategoriaDTO;
import com.marcotancredo.cursomc.services.CategoriaService;
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
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> find(@PathVariable(value = "id") Long id) {
        Categoria categoria = service.find(id);
        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping
    public ResponseEntity<Categoria> insert(@Valid @RequestBody CategoriaDTO obj) {
        Categoria categoria = service.fromDTO(obj);
        categoria = service.insert(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable(value = "id") Long id, @Valid @RequestBody CategoriaDTO objDTO) {
        Categoria obj = service.fromDTO(objDTO);
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
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<Categoria> categorias = service.findAll();
        List<CategoriaDTO> retornoCategorias = categorias.stream().map(CategoriaDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(retornoCategorias);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                       @RequestParam(value = "lines", required = false, defaultValue = "24") Integer linesPerPage,
                                                       @RequestParam(value = "orderBy", required = false, defaultValue = "nome") String orderBy,
                                                       @RequestParam(value = "direction", required = false, defaultValue = "ASC") String direction) {
        Page<Categoria> listaPaginada = service.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoriaDTO> retornoPaginado = listaPaginada.map(CategoriaDTO::new);
        return ResponseEntity.ok().body(retornoPaginado);
    }
}
