package com.ufpr.tads.web2.exceptions;

public class UsuarioSenhaInvalidoException extends Exception {

    public UsuarioSenhaInvalidoException() {
        super("Usuario ou senha inválidos.");
    }
}
