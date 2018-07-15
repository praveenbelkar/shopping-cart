package com.shoppingcart.exception;

public class InvalidAddItemException extends RuntimeException {
    public InvalidAddItemException(String message) {
        super(message);
    }
}
