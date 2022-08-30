package com.mehmetkicirti.blogapplication.utility.wrapper.abstracts;

public interface Response {
    String getMessage();
    void setMessage(String message);
    boolean getSuccess();
    void setSuccess(boolean isSuccess);
}
