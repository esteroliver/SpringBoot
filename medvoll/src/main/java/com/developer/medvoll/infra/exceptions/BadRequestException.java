package com.developer.medvoll.infra.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(){ super("Bad request exception."); }
    public BadRequestException(String message){ super(message); }
}
