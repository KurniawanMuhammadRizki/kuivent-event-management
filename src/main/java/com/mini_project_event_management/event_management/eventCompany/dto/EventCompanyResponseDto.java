package com.mini_project_event_management.event_management.eventCompany.dto;

import com.mini_project_event_management.event_management.company.dto.CompanyDto;
import com.mini_project_event_management.event_management.company.entity.Company;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EventCompanyResponseDto {
    private Long eventId;
    private CompanyDto company;
}
