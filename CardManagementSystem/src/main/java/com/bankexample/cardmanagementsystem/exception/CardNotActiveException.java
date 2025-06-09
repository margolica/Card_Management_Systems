package com.bankexample.cardmanagementsystem.exception;

public class CardNotActiveException extends RuntimeException {

    public CardNotActiveException() {
        super("Карта не активна");
    }

    public CardNotActiveException(String message) {
        super(message);
    }
}