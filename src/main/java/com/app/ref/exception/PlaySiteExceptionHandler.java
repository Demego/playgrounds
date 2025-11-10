package com.app.ref.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class PlaySiteExceptionHandler {

    @ExceptionHandler(PlaySiteNotFoundException.class)
    public ResponseEntity<String> handlePlaySiteNotFound(PlaySiteNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(PlaySiteMaxCapacityException.class)
    public ResponseEntity<String> handlePlaySiteMaxCapacity(PlaySiteMaxCapacityException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PlaySiteUserExistsException.class)
    public ResponseEntity<String> handlePlaySiteUserExists(PlaySiteUserExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PlaySiteUserNotFoundException.class)
    public ResponseEntity<String> handlePlaySiteUserNotFound(PlaySiteUserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
