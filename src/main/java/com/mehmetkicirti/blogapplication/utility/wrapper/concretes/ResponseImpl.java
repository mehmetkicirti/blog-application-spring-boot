package com.mehmetkicirti.blogapplication.utility.wrapper.concretes;

import com.mehmetkicirti.blogapplication.utility.wrapper.abstracts.Response;

public class ResponseImpl implements Response {
    private String message;
    private boolean isSuccess;

    public ResponseImpl(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public ResponseImpl(String message, boolean isSuccess) {
        this.message = message;
        this.isSuccess = isSuccess;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean getSuccess() {
        return isSuccess;
    }

    @Override
    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
