package com.mini_project_event_management.event_management.point.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class PointDto {
    private Long companyId;
    private Long point;
    private Instant expiredAt;
}
