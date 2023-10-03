package github.mmusica.webshop.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@Slf4j
@ControllerAdvice
public class CustomResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleRunntimeException(RuntimeException ex) {
        log.debug(Arrays.toString(ex.getStackTrace()));
        return new ResponseEntity<>(new RuntimeExceptionResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
