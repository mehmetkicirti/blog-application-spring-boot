package com.mehmetkicirti.blogapplication.utility.constant;

public class ExceptionConstants {
    private ExceptionConstants(){}
    public static final String USER_NOT_FOUND_EXCEPTION = "User not found with username or email: ";
    public static final String INVALID_SIGNATURE = "Invalid JWT Signature";
    public static final String INVALID_TOKEN = "Invalid JWT Token";
    public static final String EXPIRED_TOKEN = "Expired JWT Token";
    public static final String UNSUPPORTED_TOKEN = "Unsupported JWT Token";
    public static final String CLAIM_EMPTY = "JWT claims string is empty";
}
