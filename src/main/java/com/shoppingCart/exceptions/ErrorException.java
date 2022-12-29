package com.shoppingCart.exceptions;

public class ErrorException extends RuntimeException{
    public ErrorException(String error) {
        super(error);
    }

    public ErrorException() {
    }
}
