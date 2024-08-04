package com.marcotancredo.cursomc.dto;

import com.marcotancredo.cursomc.domain.Categoria;

import java.io.Serial;
import java.io.Serializable;

public class CategoriaDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nome;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
