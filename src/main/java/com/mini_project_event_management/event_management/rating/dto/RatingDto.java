package com.mini_project_event_management.event_management.rating.dto;

import com.mini_project_event_management.event_management.rating.entity.Rating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RatingDto {
    private Long userId;
    private String userName;
    private Long eventId;
    private int rating;
    private String review;
}
