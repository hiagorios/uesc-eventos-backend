package br.uesc.eventos.exception;

import org.springframework.http.HttpStatus;

public class CustomResponseException extends RuntimeException {

    private final HttpStatus status;

    public CustomResponseException(HttpStatus status) {
        this.status = status;
    }

    public CustomResponseException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public CustomResponseException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
