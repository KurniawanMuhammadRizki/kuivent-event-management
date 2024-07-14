package com.mini_project_event_management.event_management.event.service;

import com.mini_project_event_management.event_management.event.dto.EventDto;
import com.mini_project_event_management.event_management.event.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
//    Page<EventDto> getAllEventsPaginated(org.springframework.data.domain.Pageable pageable);
    EventDto creteEvent(EventDto eventDto);
    Event getEventById(Long eventId);
    Event getEventBySlug(String slug);
    EventDto getEventDtoBySlug(String slug);
    Page<EventDto> getAllEventsPaginated(String search, Pageable pageable);
}
