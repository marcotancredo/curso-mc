package com.marcotancredo.cursomc.services.exceptions;

import java.io.Serial;

public class DataIntegrityException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public DataIntegrityException(String msg) {
        super(msg);
    }

    public DataIntegrityException(Long id, Class<?> clazz) {
        super(clazz.getSimpleName() + " não encontrado(a)! Id: " + id);
    }
}