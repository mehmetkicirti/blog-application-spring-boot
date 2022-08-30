package com.mehmetkicirti.blogapplication.utility.wrapper.abstracts;

public interface ResponseData<T> extends Response {
    T getData();
    void setData(T data);
}
