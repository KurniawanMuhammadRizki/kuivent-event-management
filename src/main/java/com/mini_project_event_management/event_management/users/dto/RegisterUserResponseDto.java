package com.mini_project_event_management.event_management.users.dto;

import com.mini_project_event_management.event_management.users.entity.Users;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterUserResponseDto {
    @NotBlank(message = "Email cannot be empty")
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank(message = "Slug number cannot be empty")
    @Column(name = "slug", nullable = false)
    private String slug;

    public Users toEntity() {
        Users user = new Users();
        user.setEmail(email);
        user.setSlug(slug);
        return user;
    }

}
