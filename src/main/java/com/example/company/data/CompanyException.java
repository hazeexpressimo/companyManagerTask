package com.example.company.data;

public class CompanyException extends Exception {

    public CompanyException(String msg) {
        super(msg);
    }

    public CompanyException(Throwable t) {
        super(t);
    }

    public CompanyException() {
        super();
    }

    public CompanyException(String message, Throwable cause) {
        super(message, cause);
    }
}
