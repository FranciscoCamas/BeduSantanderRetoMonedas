package org.bedu.rest.config;

import org.bedu.rest.exeption.CurrencyAlreadyExistsException;
import org.bedu.rest.exeption.CurrencyNotFoundException;
import org.bedu.rest.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(CurrencyNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound( CurrencyNotFoundException ex ){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("Currency \t["+ ex.getName() +"] not found"));
    }

    @ExceptionHandler(CurrencyAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExits( CurrencyAlreadyExistsException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Currency \t["+ ex.getName() +"]already exits!!!"));
    }

}
