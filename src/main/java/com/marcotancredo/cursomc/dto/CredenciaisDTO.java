package com.marcotancredo.cursomc.dto;

public class CredenciaisDTO {

    private String email;
    private String senha;

    public CredenciaisDTO() {
    }

    public CredenciaisDTO(String senha, String email) {
        this.senha = senha;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
