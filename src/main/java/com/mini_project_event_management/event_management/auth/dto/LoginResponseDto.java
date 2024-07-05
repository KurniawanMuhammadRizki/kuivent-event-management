package com.mini_project_event_management.event_management.auth.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String message;
    private String token;
    private String role;
}
