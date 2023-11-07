package com.unifacisa.tasklist.exceptions;

public class UsernameAlreadyRegisteredException extends RuntimeException {

    public UsernameAlreadyRegisteredException(String msg) {
        super(msg);
    }
}