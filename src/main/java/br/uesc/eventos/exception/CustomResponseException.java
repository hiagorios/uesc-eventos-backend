package br.uesc.eventos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomResponseException extends ResponseStatusException {

    public CustomResponseException(HttpStatus status) {
        super(status);
    }

    public CustomResponseException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public CustomResponseException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }
}
