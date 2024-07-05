package com.mini_project_event_management.event_management.speakers.controller;

import com.mini_project_event_management.event_management.responses.Response;
import com.mini_project_event_management.event_management.speakers.dto.SpeakerDto;
import com.mini_project_event_management.event_management.speakers.service.SpeakersService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {
    private final SpeakersService speakersService;
    public SpeakersController(SpeakersService speakersService){
        this.speakersService = speakersService;
    }

    @PostMapping
    public ResponseEntity<Response<SpeakerDto>> addSpeaker(@Validated @RequestBody SpeakerDto speakerDto){
        var createdSpeaker = speakersService.addSpeaker(speakerDto);
        return Response.successfulResponse("Speaker created successfully" , createdSpeaker);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<Response<List<SpeakerDto>>> getListSpeakersByEventId(@PathVariable Long id){
        List<SpeakerDto> speakers = speakersService.getSpeakersByEventId(id);
        return Response.successfulResponse("Speaker fetched successfully", speakers);
    }






}
