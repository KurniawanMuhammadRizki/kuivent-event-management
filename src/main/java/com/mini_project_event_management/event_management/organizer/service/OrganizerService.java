package com.mini_project_event_management.event_management.organizer.service;

import com.mini_project_event_management.event_management.organizer.dto.RegisterOrganizerRequestDto;
import com.mini_project_event_management.event_management.organizer.dto.RegisterOrganizerResponseDto;
import com.mini_project_event_management.event_management.organizer.entity.Organizer;

public interface OrganizerService {
    RegisterOrganizerResponseDto register(RegisterOrganizerRequestDto registerOrganizerRequestDto);
    Organizer getOrganizerById(Long id);
}
