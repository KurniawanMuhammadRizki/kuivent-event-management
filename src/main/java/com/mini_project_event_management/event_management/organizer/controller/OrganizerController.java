package com.mini_project_event_management.event_management.organizer.controller;


import com.mini_project_event_management.event_management.company.dto.RegisterCompanyRequestDto;
import com.mini_project_event_management.event_management.company.dto.RegisterCompanyResponseDto;
import com.mini_project_event_management.event_management.organizer.dto.RegisterOrganizerRequestDto;
import com.mini_project_event_management.event_management.organizer.dto.RegisterOrganizerResponseDto;
import com.mini_project_event_management.event_management.organizer.service.OrganizerService;
import com.mini_project_event_management.event_management.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/organizer")
public class OrganizerController {
    private final OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService){
        this.organizerService = organizerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Response<RegisterOrganizerResponseDto>> register(@RequestBody RegisterOrganizerRequestDto registerDto) {
        var organizerRegistered = organizerService.register(registerDto);
        return Response.successfulResponse("User registered successfully", organizerRegistered);
    }
}
