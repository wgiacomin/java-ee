package com.ufpr.tads.web2.exceptions;

public class OrdenacaoInvalidaException extends Exception {

    public OrdenacaoInvalidaException() {
        super("A ordenação deve ser DESC ou ASC");
    }
}
