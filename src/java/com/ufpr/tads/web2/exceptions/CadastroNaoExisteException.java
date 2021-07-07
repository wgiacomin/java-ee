package com.ufpr.tads.web2.exceptions;

public class CadastroNaoExisteException extends Exception {

    public CadastroNaoExisteException() {
        super("O cadastro não foi encontrado.");
    }
}
