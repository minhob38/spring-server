package minho.springserver.exception;

import minho.springserver.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class Advice {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AuthException.class})
    public ErrorResponse unauthorizedErrorHandler(AuthException e) {
        System.out.println("auth exception handler");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }
}
