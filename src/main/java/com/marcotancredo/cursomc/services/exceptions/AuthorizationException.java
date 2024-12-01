package com.marcotancredo.cursomc.services.exceptions;

import java.io.Serial;

public class AuthorizationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public AuthorizationException(String msg) {
        super(msg);
    }
}