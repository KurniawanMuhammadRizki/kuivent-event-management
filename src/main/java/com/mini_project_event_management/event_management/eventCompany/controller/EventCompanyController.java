package com.mini_project_event_management.event_management.eventCompany.controller;

import com.mini_project_event_management.event_management.eventCompany.dto.EventCompanyRequestDto;
import com.mini_project_event_management.event_management.eventCompany.dto.EventCompanyResponseDto;
import com.mini_project_event_management.event_management.eventCompany.service.EventCompanyService;
import com.mini_project_event_management.event_management.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event-company")
public class EventCompanyController {

    private final EventCompanyService eventCompanyService;

    public EventCompanyController(EventCompanyService eventCompanyService){
        this.eventCompanyService = eventCompanyService;
    }

    @PostMapping
    public ResponseEntity<Response<Object>> addEventCompany(@Validated @RequestBody EventCompanyRequestDto eventCompanyRequestDto){
        eventCompanyService.addEventCompany(eventCompanyRequestDto);
        return Response.successfulResponse("Event Company added successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<List<EventCompanyResponseDto>>> getEventCompanyByEventId(@PathVariable Long id){
        var eventCompanies = eventCompanyService.getEventCompanyByEventId(id);
        return Response.successfulResponse("Event Company fetched successfully" , eventCompanies);
    }

}
