package com.bankexample.cardmanagementsystem.exception;

public class CardNotFoundException extends RuntimeException {

    public CardNotFoundException() {
        super("Карта не найдена");
    }

    public CardNotFoundException(String message) {
        super(message);
    }

    public CardNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}