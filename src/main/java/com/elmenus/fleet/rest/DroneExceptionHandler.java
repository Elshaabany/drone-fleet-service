package com.elmenus.fleet.rest;

import com.elmenus.fleet.exception.DroneLoadingException;
import com.elmenus.fleet.exception.DroneNotLoadedException;
import com.elmenus.fleet.exception.DuplicateSerialNumberException;
import com.elmenus.fleet.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class DroneExceptionHandler {

    @ExceptionHandler(DuplicateSerialNumberException.class)
    public ResponseEntity<DroneErrorResponse> handleDuplicateSerialNumberException(DuplicateSerialNumberException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new DroneErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage(), System.currentTimeMillis()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<DroneErrorResponse> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DroneErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), System.currentTimeMillis()));
    }

    @ExceptionHandler(DroneLoadingException.class)
    public ResponseEntity<DroneErrorResponse> handleDroneLoadingException(DroneLoadingException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DroneErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), System.currentTimeMillis()));
    }

    @ExceptionHandler(DroneNotLoadedException.class)
    public ResponseEntity<DroneErrorResponse> handleDroneNotLoadedException(DroneNotLoadedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DroneErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), System.currentTimeMillis()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DroneErrorResponse> handleOtherExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DroneErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred", System.currentTimeMillis()));
    }
}
