package com.ufpr.tads.web2.exceptions;

public class OrdenacaoInvalidaException extends Exception {

    public OrdenacaoInvalidaException() {
        super("Especifique DESC ou ASC como parâmetro de ordenação.");
    }
}
