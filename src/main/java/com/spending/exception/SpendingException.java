package com.spending.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpendingException extends RuntimeException {


    public SpendingException(String message) {
        super(message);
        this.message = message;
    }

    public SpendingException(String message, String field) {
        super(message);
        this.message = message;
        this.field = field;
    }

    public SpendingException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public SpendingException(Throwable cause) {
        super(cause);
    }

    public SpendingException(Throwable cause, String message, String field) {
        super(cause);
        this.message = message;
        this.field = field;
    }

    public SpendingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
    }

    private String message;

    private String field;
}
