package com.marcotancredo.cursomc.services.exceptions;

import java.io.Serial;

public class ObjectNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(String msg) {
        super(msg);
    }

    public ObjectNotFoundException(Long id, Class<?> clazz) {
        super(clazz.getSimpleName() + " não encontrado(a)! Id: " + id);
    }
}
