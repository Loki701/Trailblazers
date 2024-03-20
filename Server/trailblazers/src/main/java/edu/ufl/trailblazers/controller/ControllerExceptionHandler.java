package edu.ufl.trailblazers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ControllerExceptionHandler {
    // Return more human-readable API response when JSON deserialization fails for any request expecting a body.
    // Called when request body has invalid JSON or the expected properties have unexpected value types.
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleJsonDeserializationException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("JSON deserialization failed. " +
                "Verify that the request body matches the expected input exactly. " + e.getMessage() + ".");
    }

    // Return more human-readable API response when a path parameter is of an unexpected data type.
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleWrongPathParamType(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Received unexpected data type for path parameter \""
                + e.getParameter().getParameterName() + "\". " + e.getMessage() + ".");
    }
}
