package com.mini_project_event_management.event_management.exceptions;

import com.mini_project_event_management.event_management.responses.Response;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.UnknownHostException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public final ResponseEntity<Response<String>> handleProductNotFoundException(DataNotFoundException ex){
        return Response.failedResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
    @ExceptionHandler(AlreadyExistException.class)
    public final ResponseEntity<Response<String>> handleAlreadyExistException(AlreadyExistException ex){
        return Response.failedResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ExceptionHandler(ApplicationException.class)
    public final ResponseEntity<Response<String>> handleProductNotFoundException(ApplicationException ex){
        return Response.failedResponse(HttpStatus.BAD_REQUEST.value(), "Unable to process the request", ex.getMessage());
    }
    @ExceptionHandler(EmptyDataException.class)
    public final ResponseEntity<Response<String>> handleEmptyDataException(EmptyDataException ex){
        return Response.successfulResponse( ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Response<String>> handleValidationExceptions(MethodArgumentNotValidException ex){
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return Response.failedResponse(HttpStatus.BAD_REQUEST.value(), "Unable to process the request. Error: " + errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Response<String>> handleAllExceptions(Exception ex) {
        if (ex.getCause() instanceof UnknownHostException) {
            return Response.failedResponse(HttpStatus.BAD_REQUEST.value(), "Unable to process the request: " + ex.getLocalizedMessage());
        }
        return Response.failedResponse(HttpStatus.BAD_REQUEST.value(), "Unable to process the request: " + ex.getMessage());
    }
}
