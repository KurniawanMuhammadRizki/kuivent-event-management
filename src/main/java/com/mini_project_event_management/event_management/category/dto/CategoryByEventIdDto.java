package com.mini_project_event_management.event_management.category.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryByEventIdDto {
    @NotNull(message = "Event id cannot be empty")
    private Long eventId;
}
