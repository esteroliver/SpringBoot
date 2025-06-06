package com.developer.medvoll.infra.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(){ super("Entity not found."); }

    public NotFoundException(String message){ super(message); }
}
