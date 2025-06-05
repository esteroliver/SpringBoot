package com.developer.medvoll.utils.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(){ super("Entity not found."); }

    public NotFoundException(String message){ super(message); }
}
