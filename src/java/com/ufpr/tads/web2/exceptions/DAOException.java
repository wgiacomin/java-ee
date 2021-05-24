package com.ufpr.tads.web2.exceptions;

public class DAOException extends Exception {
    public DAOException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public DAOException(String string) {
        super(string);
    }
}
    
