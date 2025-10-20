package com.vishnu.util.http;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record HttpErrorInfo(
        ZonedDateTime timestamp,
        String path,
        HttpStatus httpStatus,
        String message
) {
    public HttpErrorInfo(HttpStatus httpStatus, String path, String message) {
        this(ZonedDateTime.now(), path, httpStatus, message);
    }

    public int status() {
        return httpStatus != null ? httpStatus.value() : 0;
    }

    public String error() {
        return httpStatus != null ? httpStatus.getReasonPhrase() : null;
    }
}
