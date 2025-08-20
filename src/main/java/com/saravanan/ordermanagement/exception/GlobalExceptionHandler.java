package com.saravanan.ordermanagement.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Sarav on 14 Jul 2025
 * @project govtech
 * @package com.saravanan.ordermanagement.exception
 * @class GlobalExceptionHandler
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

//    @ExceptionHandler(BadRequestException.class)
//    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {
//        ErrorResponse error = new ErrorResponse(
//                400,
//                ex.getMessage(),
//                LocalDateTime.now()
//        );
//        return ResponseEntity.badRequest().body(error);
//    }
}

