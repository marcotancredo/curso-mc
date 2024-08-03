package com.marcotancredo.cursomc.domain.enums;

public enum TipoCliente {
    PESSOA_FISICA(1, "Pessoa física"),
    PESSOA_JURIDICA(2, "Pessoa jurídica");

    private final int cod;
    private final String descricao;

    TipoCliente(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (TipoCliente value : TipoCliente.values()) {
            if (cod.equals(value.getCod())) {
                return value;
            }
        }

        throw new IllegalArgumentException("Código enum inválido: " + cod);
    }
}
