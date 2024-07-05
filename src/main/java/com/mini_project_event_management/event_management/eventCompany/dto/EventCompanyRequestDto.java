package com.mini_project_event_management.event_management.eventCompany.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EventCompanyRequestDto {
    @NotNull(message = "Company id  is required")
    private Long companyId;
    @NotNull(message = "Event id  is required")
    private Long eventId;
}
