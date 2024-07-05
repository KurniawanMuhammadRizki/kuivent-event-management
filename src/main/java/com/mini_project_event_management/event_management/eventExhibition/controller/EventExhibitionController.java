package com.mini_project_event_management.event_management.eventExhibition.controller;

import com.mini_project_event_management.event_management.eventExhibition.dto.EventExhibitionDto;
import com.mini_project_event_management.event_management.eventExhibition.service.EventExhibitionService;
import com.mini_project_event_management.event_management.responses.Response;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/event-exhibition")
public class EventExhibitionController {
    private final EventExhibitionService eventExhibitionService;
    public EventExhibitionController(EventExhibitionService eventExhibitionService){
        this.eventExhibitionService = eventExhibitionService;
    }

    @PostMapping
    @RateLimiter(name = "default")
    public ResponseEntity<Response<Object>> addEventExhibition(@Validated @RequestBody EventExhibitionDto eventExhibitionDto){
        eventExhibitionService.addEventExhibition(eventExhibitionDto);
        return  Response.successfulResponse("Event Exhibition added successfully");
    }
}
