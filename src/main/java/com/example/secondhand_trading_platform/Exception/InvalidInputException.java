package com.example.secondhand_trading_platform.Exception;

public class InvalidInputException extends RuntimeException {
    private int statusCode;

    public InvalidInputException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
