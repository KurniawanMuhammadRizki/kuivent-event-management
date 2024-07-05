package com.mini_project_event_management.event_management.eventTopic.controller;

import com.mini_project_event_management.event_management.eventTopic.dto.EventTopicAddRequestDto;
import com.mini_project_event_management.event_management.eventTopic.dto.EventTopicResponseDto;
import com.mini_project_event_management.event_management.eventTopic.service.EventTopicService;
import com.mini_project_event_management.event_management.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event-topic")
public class EventTopicController {
    private final EventTopicService eventTopicService;
    public EventTopicController(EventTopicService eventTopicService){
        this.eventTopicService = eventTopicService;
    }

    @PostMapping
    public ResponseEntity<Response<Object>> addEventTopic(@Validated @RequestBody EventTopicAddRequestDto eventTopicAddRequestDto){
        eventTopicService.addEventTopic(eventTopicAddRequestDto);
        return Response.successfulResponse("Event Company added successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<List<EventTopicResponseDto>>> getEventTopicByEventId(@PathVariable Long id){
        var eventTopics = eventTopicService.getEventTopicByEventId(id);
        return Response.successfulResponse("Event Topic fetched successfully", eventTopics);
    }

}
