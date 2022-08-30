package com.mehmetkicirti.blogapplication.utility.wrapper.concretes;

import com.mehmetkicirti.blogapplication.utility.wrapper.abstracts.ResponseData;

public class ResponseDataImpl<T> extends ResponseImpl implements ResponseData<T> {

    private T data;

    public ResponseDataImpl(T data) {
        super(true);
        this.data =data;
    }

    public ResponseDataImpl(String message, T data) {
        super(message,true);
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }
}
