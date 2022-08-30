package com.mehmetkicirti.blogapplication.utility.wrapper.abstracts;

import org.springframework.http.HttpStatus;

public interface ErrorResponse extends Response{
    HttpStatus getStatusCode();
    void setStatusCode(HttpStatus statusCode);
}
