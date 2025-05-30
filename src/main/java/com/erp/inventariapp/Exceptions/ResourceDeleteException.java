package com.erp.inventariapp.Exceptions;

public class ResourceDeleteException extends RuntimeException {
    public ResourceDeleteException(String message, Exception e){
        super(message, e);
    }
}
