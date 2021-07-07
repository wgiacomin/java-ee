package com.ufpr.tads.web2.exceptions;

public class BeanInvalidoException extends Exception {

    public BeanInvalidoException() {
        super("O Bean informado não é válido.");
    }
}
