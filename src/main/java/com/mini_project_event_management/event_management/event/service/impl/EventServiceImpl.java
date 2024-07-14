package com.mini_project_event_management.event_management.event.service.impl;

import com.mini_project_event_management.event_management.category.dto.CategoryDto;
import com.mini_project_event_management.event_management.category.service.CategoryService;
import com.mini_project_event_management.event_management.event.dto.EventDto;
import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.event.repository.EventRepository;
import com.mini_project_event_management.event_management.event.service.EventService;
import com.mini_project_event_management.event_management.eventType.entity.EventType;
import com.mini_project_event_management.event_management.eventType.service.EventTypeService;
import com.mini_project_event_management.event_management.exceptions.DataNotFoundException;
import com.mini_project_event_management.event_management.helpers.SlugifyHelper;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventTypeService eventTypeService;


    public EventServiceImpl(EventRepository eventRepository, EventTypeService eventTypeService){
        this.eventRepository = eventRepository;
        this.eventTypeService = eventTypeService;
    }

    @Override
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    @Override
    @Cacheable(value = "getEventById", key = "#eventId")
    public Event getEventById(Long eventId){
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isEmpty()) {
            throw new DataNotFoundException("Event not found");
        }
        return event.orElse(null);
    }

    @Override
    @Cacheable(value = "getEventBySlug", key = "#slug")
    public Event getEventBySlug(String slug){
        Optional<Event> event = eventRepository.findBySlug(slug);
        if (event.isEmpty()) {
            throw new DataNotFoundException("Event not found");
        }
        return event.orElse(null);
    }

    @Override
    public EventDto getEventDtoBySlug(String slug){
        Event event = getEventBySlug(slug);
        EventDto eventDto = event.toEventDto();
        return eventDto;
    }

    @Override
    public Page<EventDto> getAllEventsPaginated(String search, Pageable pageable) {
        if (search != null && !search.isEmpty()) {
            return eventRepository.findByNameContainingIgnoreCaseOrCityContainingIgnoreCase(search, search, pageable)
                    .map(Event::toEventDto);
        } else {
            return eventRepository.findAll(pageable)
                    .map(Event::toEventDto);
        }
    }
//    @Override
//    public Page<EventDto> getAllEventsPaginated(Pageable pageable) {
//        return eventRepository.findAll(pageable)
//                .map(Event::toEventDto);
//    }

    @Override
    public EventDto creteEvent(EventDto eventDto){
        Event event = eventDtoToEvent(eventDto);
        eventRepository.save(event);
        return eventDto;
    }

    private Event eventDtoToEvent(EventDto eventDto){
        Instant now = Instant.now();
        EventType eventType = eventTypeService.getEventTypeById(Math.toIntExact(eventDto.getEventTypeId()));
        String slug = SlugifyHelper.slugify(eventDto.getName());
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setCity(eventDto.getCity());
        event.setAddress(eventDto.getAddress());
        event.setDescription(eventDto.getDescription());
        event.setCapacity(eventDto.getCapacity());
        event.setImageUrl(eventDto.getImageUrl());
        event.setWebsiteUrl(eventDto.getWebsiteUrl());
        event.setDateStart(eventDto.getDateStart());
        event.setDateEnd(eventDto.getDateEnd());
        event.setHourStart(eventDto.getHourStart());
        event.setHourEnd(eventDto.getHourEnd());
        event.setEventType(eventType);
        event.setSlug(slug);
        event.setCreatedAt(now);
        event.setUpdatedAt(now);

        return event;
    }

}
