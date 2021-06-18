package com.ataccama.dbadger.config.web;

import com.ataccama.dbadger.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorResponse> handleIllegalArgumentException(RuntimeException ex) {
        return handle(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(IllegalStateException.class)
    protected ResponseEntity<ErrorResponse> handleIllegalStateException(RuntimeException ex) {
        return handle(HttpStatus.CONFLICT, ex);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNotFoundException(RuntimeException ex) {
        return handle(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleGenericException(RuntimeException ex) {
        return handle(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    private ResponseEntity<ErrorResponse> handle(HttpStatus status, Exception ex) {
        var uuid = UUID.randomUUID();
        logger.error(uuid.toString(), ex);
        return ResponseEntity.of(Optional.of(new ErrorResponse(uuid, status, ex.getMessage(), LocalDateTime.now())));
    }

    record ErrorResponse(UUID traceId, HttpStatus status, String message, LocalDateTime timestamp) {
    }
}