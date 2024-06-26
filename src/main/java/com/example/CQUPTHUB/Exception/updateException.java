package com.example.CQUPTHUB.Exception;

public class updateException extends RuntimeException {
    private final int statusCode = 200;

    public updateException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
