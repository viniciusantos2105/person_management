package com.person.management.exceptions;

import com.person.management.exceptions.commons.DateInvalidException;
import com.person.management.exceptions.commons.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourceExceptionHandlerTest {

    private final ResourceExceptionHandler handler = new ResourceExceptionHandler();

    @Test
    public void testNotFoundError() {
        // Arrange
        NotFoundException exception = NotFoundException.createNotFoundException();
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Act
        ResponseEntity<ApiError> response = handler.notFoundError(exception, request);

        // Assert
        assertEquals(NotFoundException.DEFAULT_STATUS, response.getStatusCodeValue());
        assertEquals(exception.getMessage(), response.getBody().getError());
    }

    @Test
    public void testDateInvalidError() {
        // Arrange
        DateInvalidException exception = DateInvalidException.createDateInvalidException();
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Act
        ResponseEntity<ApiError> response = handler.dateInvalidError(exception, request);

        // Assert
        assertEquals(DateInvalidException.DEFAULT_STATUS, response.getStatusCodeValue());
        assertEquals(exception.getMessage(), response.getBody().getError());
    }


}