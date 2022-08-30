package com.mehmetkicirti.blogapplication.utility.wrapper;

import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorDetails {
    private final Date timestamp;
    private final String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
    public ErrorDetails(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }
}
