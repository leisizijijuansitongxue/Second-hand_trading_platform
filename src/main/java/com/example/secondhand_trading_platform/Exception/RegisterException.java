package com.example.secondhand_trading_platform.Exception;

public class RegisterException extends RuntimeException{
    private final int statusCode = 301;

    public RegisterException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
