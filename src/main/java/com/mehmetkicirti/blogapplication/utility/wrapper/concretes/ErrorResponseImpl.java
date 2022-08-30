package com.mehmetkicirti.blogapplication.utility.wrapper.concretes;

import com.mehmetkicirti.blogapplication.utility.wrapper.abstracts.ErrorResponse;
import org.springframework.http.HttpStatus;

public class ErrorResponseImpl extends ResponseImpl implements ErrorResponse{
    private HttpStatus statusCode;

    public ErrorResponseImpl() {
        super(false);
    }

    public ErrorResponseImpl(HttpStatus statusCode) {
        super(false);
        this.statusCode = statusCode;
    }

    public ErrorResponseImpl(HttpStatus statusCode, String message) {
        super(message, false);
        this.statusCode = statusCode;
    }

    @Override
    public HttpStatus getStatusCode() {
        return statusCode;
    }

    @Override
    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
