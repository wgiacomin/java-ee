package com.ufpr.tads.web2.exceptions;

public class RegistroInexistenteException extends Exception {

    public RegistroInexistenteException() {
        super("Categoria não existe.");
    }
}
