package com.ufpr.tads.web2.exceptions;

public class DAOVException extends Exception {
    public DAOVException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public DAOVException(String string) {
        super(string);
    }
}
    
