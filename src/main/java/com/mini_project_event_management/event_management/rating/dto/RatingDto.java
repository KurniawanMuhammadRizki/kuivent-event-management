package com.mini_project_event_management.event_management.rating.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RatingDto {
    private Long userId;
    private Long eventId;
    private int rating;
    private String review;

}
