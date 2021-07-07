package com.ufpr.tads.web2.exceptions;


public class FacadeException extends Exception {
        public FacadeException() {
    }

    public FacadeException(String string) {
        super(string);
    }

    public FacadeException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
}
