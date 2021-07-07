package com.ufpr.tads.web2.exceptions;

public class CadastroDuplicadoException extends Exception {

    public CadastroDuplicadoException() {
        super("O login já existe. Não é possível criar dois registros com o mesmo login.");
    }
}
