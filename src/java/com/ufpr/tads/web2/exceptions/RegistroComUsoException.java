package com.ufpr.tads.web2.exceptions;

public class RegistroComUsoException extends Exception {

    public RegistroComUsoException(int registros) {
        super("Existem " + registros + " cadastrados com esse perfil. Altere-os para que seja possível apagá-los.");
    }

    public RegistroComUsoException() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
