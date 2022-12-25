package minho.springserver.exception;

import minho.springserver.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Advice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BoardException.class)
    public ErrorResponse boardExceptionHandler(BoardException e) {
        System.out.println("controller advice");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        System.out.println("controller advice");
        BindingResult bindingResult = e.getBindingResult();
        ErrorResponse errorResponse = new ErrorResponse();
        FieldError fieldError = bindingResult.getFieldError();

        if (fieldError != null) {
            String message = fieldError.getField() + "= " + fieldError.getDefaultMessage();
            errorResponse.setMessage(message);
            return errorResponse;
        }

        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }
}
