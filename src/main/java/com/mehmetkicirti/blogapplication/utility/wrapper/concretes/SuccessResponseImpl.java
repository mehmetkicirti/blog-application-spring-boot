package com.mehmetkicirti.blogapplication.utility.wrapper.concretes;

import com.mehmetkicirti.blogapplication.utility.wrapper.abstracts.Response;

public class SuccessResponseImpl extends ResponseImpl implements Response {
    public SuccessResponseImpl() {
        super(true);
    }

    public SuccessResponseImpl(String message) {
        super(message, true);
    }
}
