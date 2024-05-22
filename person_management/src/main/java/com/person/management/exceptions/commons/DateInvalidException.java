package com.person.management.exceptions.commons;

public class DateInvalidException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = " Formato de data invalido";
    public static final int DEFAULT_STATUS = 400;

    DateInvalidException(String message) {
        super(message);
    }

    public static DateInvalidException createDateInvalidException(String message) {
        return new DateInvalidException(message);
    }

    public static DateInvalidException createDateInvalidException() {
        return new DateInvalidException(DEFAULT_MESSAGE);
    }
}
