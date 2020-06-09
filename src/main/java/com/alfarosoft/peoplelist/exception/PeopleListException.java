package com.alfarosoft.peoplelist.exception;

public class PeopleListException extends RuntimeException{

    private Integer status;

    public PeopleListException(Integer status) {
        super();
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
