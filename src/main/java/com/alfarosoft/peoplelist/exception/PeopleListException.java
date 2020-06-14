package com.alfarosoft.peoplelist.exception;

public class PeopleListException extends RuntimeException{

    private String message;

    public PeopleListException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
