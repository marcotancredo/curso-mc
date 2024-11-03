package com.marcotancredo.cursomc.resources;

import com.marcotancredo.cursomc.domain.Produto;
import com.marcotancredo.cursomc.dto.ProdutoDTO;
import com.marcotancredo.cursomc.resources.utils.URL;
import com.marcotancredo.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Produto> find(@PathVariable(value = "id") Long id) {
        Produto produto = service.find(id);
        return ResponseEntity.ok().body(produto);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
                                                     @RequestParam(value = "categorias", required = false, defaultValue = "") String categorias,
                                                     @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                     @RequestParam(value = "lines", required = false, defaultValue = "24") Integer linesPerPage,
                                                     @RequestParam(value = "orderBy", required = false, defaultValue = "nome") String orderBy,
                                                     @RequestParam(value = "direction", required = false, defaultValue = "ASC") String direction) {
        List<Long> categoriaIds = URL.decodeLongList(categorias);
        String nomeDecoded = URL.decodeParam(nome);

        Page<Produto> listaPaginada = service.search(nomeDecoded, categoriaIds, page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> retornoPaginado = listaPaginada.map(ProdutoDTO::new);
        return ResponseEntity.ok().body(retornoPaginado);
    }
}
