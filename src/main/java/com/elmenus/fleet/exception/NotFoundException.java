package com.elmenus.fleet.exception;

public class NotFoundException extends RuntimeException{
    NotFoundException(String entityName, Object id) {
        super(String.format("%s not found - %s", entityName, id.toString()));
    }
}
