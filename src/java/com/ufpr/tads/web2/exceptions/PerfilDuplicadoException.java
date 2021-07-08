package com.ufpr.tads.web2.exceptions;

public class PerfilDuplicadoException extends Exception {

    public PerfilDuplicadoException() {
        super("O Perfil já existe. Não é possível criar dois registros com o mesmo perfil.");
    }
}
