package com.person.management.exceptions.commons;

public class NotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Recurso não encontrado";
    public static final int DEFAULT_STATUS = 404;

    private NotFoundException(String message) {
        super(message);
    }

    public static NotFoundException createNotFoundException(String message) {
        return new NotFoundException(message);
    }

    public static NotFoundException createNotFoundException() {
        return new NotFoundException(DEFAULT_MESSAGE);
    }
}
