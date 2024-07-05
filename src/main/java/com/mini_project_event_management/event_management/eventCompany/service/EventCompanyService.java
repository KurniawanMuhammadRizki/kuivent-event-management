package com.mini_project_event_management.event_management.eventCompany.service;

import com.mini_project_event_management.event_management.eventCompany.dto.EventCompanyRequestDto;
import com.mini_project_event_management.event_management.eventCompany.dto.EventCompanyResponseDto;

import java.util.List;

public interface EventCompanyService {
    void addEventCompany(EventCompanyRequestDto eventCompanyRequestDto);
    List<EventCompanyResponseDto> getEventCompanyByEventId(Long id);
}
