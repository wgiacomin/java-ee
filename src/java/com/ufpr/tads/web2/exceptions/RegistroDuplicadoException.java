package com.ufpr.tads.web2.exceptions;

public class RegistroDuplicadoException extends Exception {

    public RegistroDuplicadoException() {
        super("O email já está cadastrado. Não é possível criar dois registros com o mesmo email.");
    }
}
