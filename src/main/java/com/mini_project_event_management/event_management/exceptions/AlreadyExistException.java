package com.mini_project_event_management.event_management.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@EqualsAndHashCode(callSuper = false)
@Setter
@Data
public class AlreadyExistException extends RuntimeException {

    public AlreadyExistException(String message){
        super(message);
    }
}
