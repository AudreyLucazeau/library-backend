package com.home.library.exceptions;

public class NotFoundException extends RuntimeException{

    private String notFoundMessage;

    public NotFoundException(String notFoundMessage) {
        this.notFoundMessage = notFoundMessage;
    }

    public String getNotFoundMessage() {
        return notFoundMessage;
    }
}
