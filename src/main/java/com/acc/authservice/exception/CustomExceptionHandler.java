package com.acc.authservice.exception;

import com.acc.authservice.model.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(CustomException.class)
//    public ResponseEntity<ErrorMessage>
}
