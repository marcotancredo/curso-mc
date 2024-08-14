package com.marcotancredo.cursomc.dto;

import com.marcotancredo.cursomc.domain.Cliente;
import com.marcotancredo.cursomc.services.validation.ClienteUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

@ClienteUpdate
public class ClienteDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;

    @Email(message = "Email inválido")
    @NotEmpty(message = "Preenchimento obrigatório")
    private String email;

    public ClienteDTO() {

    }

    public ClienteDTO(Cliente obj) {
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
