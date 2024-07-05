package com.mini_project_event_management.event_management.point.dto;

import com.mini_project_event_management.event_management.point.entity.Point;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddPointDto {

    @NotBlank(message = "Company id  is required")
    private Long companyId;

    @NotNull(message = "Point amount cannot be null")
    @Min(value = 0, message = "Point amount must be zero or positive")
    private int point;
}
