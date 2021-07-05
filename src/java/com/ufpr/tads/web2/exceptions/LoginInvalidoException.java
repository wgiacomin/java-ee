package com.ufpr.tads.web2.exceptions;

public class LoginInvalidoException extends Exception {

    public LoginInvalidoException() {
        super("Não foi possível encontrar o usuário no banco de dados.");
    }
}
