package com.marcotancredo.cursomc.domain.enums;

public enum Perfil {
    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private final int cod;
    private final String descricao;

    Perfil(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Perfil value : Perfil.values()) {
            if (cod.equals(value.getCod())) {
                return value;
            }
        }

        throw new IllegalArgumentException("Código enum inválido: " + cod);
    }
}
