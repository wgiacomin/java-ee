package com.ufpr.tads.web2.exceptions;

public class RegistroDuplicadoException extends Exception {

    public RegistroDuplicadoException() {
        super("O login já existe. Não é possível criar dois registros com o mesmo login.");
    }
}
