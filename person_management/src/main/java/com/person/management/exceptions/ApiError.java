package com.person.management.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;

    public static ApiError createGenericError(LocalDateTime timestamp, Integer status, String error, String path) {
        return new ApiError(timestamp, status, error, path);
    }
}
