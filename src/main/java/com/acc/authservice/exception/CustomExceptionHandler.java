package com.acc.authservice.exception;

import com.acc.authservice.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorMessage> handleCustomException(CustomException customException) {
        return new ResponseEntity<>(new ErrorMessage()
                .builder()
                .error(customException.getError())
                .message(customException.getMessage())
                .build(), HttpStatusCode.valueOf(customException.getStatus())
        );
    }
}
