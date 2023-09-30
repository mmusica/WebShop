package github.mmusica.webshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleRunntimeException(RuntimeException ex) {
        return new ResponseEntity<>(new RuntimeExceptionResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
