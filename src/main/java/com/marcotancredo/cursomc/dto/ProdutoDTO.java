package com.marcotancredo.cursomc.dto;

import com.marcotancredo.cursomc.domain.Produto;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

public class ProdutoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private Double preco;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty(message = "Preenchimento obrigatório") @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres") String getNome() {
        return nome;
    }

    public void setNome(@NotEmpty(message = "Preenchimento obrigatório") @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres") String nome) {
        this.nome = nome;
    }

    public @NotEmpty(message = "Preenchimento obrigatório") @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres") Double getPreco() {
        return preco;
    }

    public void setPreco(@NotEmpty(message = "Preenchimento obrigatório") @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres") Double preco) {
        this.preco = preco;
    }
}
