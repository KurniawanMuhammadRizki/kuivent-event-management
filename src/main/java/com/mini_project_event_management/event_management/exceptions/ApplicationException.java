package com.mini_project_event_management.event_management.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
@EqualsAndHashCode(callSuper = true)
@Setter
@Data
public class ApplicationException extends RuntimeException {
    private HttpStatus httpStatus;
    private List<String> errors;
    private Objects data;

    public ApplicationException(String message) {
        this(HttpStatus.BAD_REQUEST, message);
    }

    public ApplicationException(HttpStatus httpStatus, String message) {
        this(httpStatus, message, Collections.singletonList(message), null);
    }

    public ApplicationException(HttpStatus httpStatus, String message, List<String> errors, Object data) {
        super(message);
        this.httpStatus = httpStatus;
        this.errors = errors;
         this.data = (Objects) data;
    }
}
