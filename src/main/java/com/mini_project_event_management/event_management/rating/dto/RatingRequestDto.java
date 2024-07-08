package com.mini_project_event_management.event_management.rating.dto;

import com.mini_project_event_management.event_management.rating.entity.Rating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class RatingRequestDto {
    @NotNull(message = "User id type cannot be null")
    private Long userId;

    @NotNull(message = "Event id type cannot be null")
    private Long eventId;

    @NotNull(message = "rating type cannot be null")
    private int rating;

    @NotBlank(message = "review cannot be empty")
    private String review;
}
