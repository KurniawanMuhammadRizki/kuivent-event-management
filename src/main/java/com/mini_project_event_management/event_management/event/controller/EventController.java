package com.mini_project_event_management.event_management.event.controller;

import com.mini_project_event_management.event_management.event.dto.EventDto;
import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.event.service.EventService;
import com.mini_project_event_management.event_management.responses.Response;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @GetMapping
    @RateLimiter(name = "default")
    public ResponseEntity<Response<List<Event>>> getAllEvents(){
        List<Event> events = eventService.getAllEvents();
        return Response.successfulResponse("All event fetched", events);
    }

    @GetMapping("/paginated")
    @Transactional
    public ResponseEntity<Response<org.springframework.data.domain.Page<EventDto>>> getAllEventsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Page<EventDto> eventsPage = eventService.getAllEventsPaginated(PageRequest.of(page, size));
        return Response.successfulResponse("Events fetched with pagination", eventsPage);
    }

    @PostMapping("/create-event")
    public ResponseEntity<Response<EventDto>> createEvent(@Validated @RequestBody EventDto eventDto){
        var createdEvent = eventService.creteEvent(eventDto);
        return Response.successfulResponse("Event created successfully", createdEvent);
    }



}
