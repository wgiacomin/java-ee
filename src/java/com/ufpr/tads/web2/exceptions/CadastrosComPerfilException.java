package com.ufpr.tads.web2.exceptions;

public class CadastrosComPerfilException extends Exception {

    public CadastrosComPerfilException(int registros) {
        super("Existem " + registros + " cadastrados com esse perfil. Altere-os para que seja possível apagá-los.");
    }
}
