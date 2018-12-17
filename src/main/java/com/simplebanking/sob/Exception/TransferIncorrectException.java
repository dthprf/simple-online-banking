package com.simplebanking.sob.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TransferIncorrectException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TransferIncorrectException(String message) {
        super(message);
    }

    public TransferIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }
}
