package com.unifacisa.tasklist.exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException {

    public EmailAlreadyRegisteredException(String msg) {
        super(msg);
    }
}