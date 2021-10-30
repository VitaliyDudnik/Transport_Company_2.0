package com.example.transport_company.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.UnexpectedTypeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionController {
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss,SSS");

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFound(ResourceNotFoundException ex, HttpServletRequest req) {
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now().format(format),
                req.getRequestURL(),
                ex.getMessage(),
                "error-0001");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ExceptionResponse> alreadyExists(ResourceConflictException ex, HttpServletRequest req) {
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now().format(format),
                req.getRequestURL(),
                ex.getMessage(),
                "error-0002");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionResponse> userNotFound(NoSuchElementException ex, HttpServletRequest req) {
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now().format(format),
                req.getRequestURL(),
                "Oops! Element being requested does not exist",
                "error-0003");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> userNotFound(RuntimeException ex, HttpServletRequest req) {
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now().format(format),
                req.getRequestURL(),
                "Oops! Something went wrong",
                "error-0004");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<ExceptionResponse> validationEx(UnexpectedTypeException ex, HttpServletRequest req) {
        ExceptionResponse response
                = new ExceptionResponse(LocalDateTime.now().format(format),
                req.getRequestURL(),
                "Not valid request parameter",
                "error-0005");
        return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> notUnique(DataIntegrityViolationException ex, HttpServletRequest req) {
        ExceptionResponse response
                = new ExceptionResponse(LocalDateTime.now().format(format),
                req.getRequestURL(),
                "Not unique parameter.",
                "error-0006");
        return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> nullPointer(NullPointerException ex, HttpServletRequest req) {
        ExceptionResponse response
                = new ExceptionResponse(LocalDateTime.now().format(format),
                req.getRequestURL(),
                "Oops! Element being requested does not exist",
                "error-0007");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> ValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> notReadableEx(HttpMessageNotReadableException ex, HttpServletRequest req) {
        ExceptionResponse response
                = new ExceptionResponse(LocalDateTime.now().format(format),
                req.getRequestURL(),
                "Not readable request",
                "error-0009");
        return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
    }
}
