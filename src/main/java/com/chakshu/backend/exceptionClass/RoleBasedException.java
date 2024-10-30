package com.chakshu.backend.exceptionClass;

public class RoleBasedException extends RuntimeException{
    public RoleBasedException (String message,Throwable cause) {
        super(message,cause);
    }
    public RoleBasedException (String message) {
        super(message);
    }
}
