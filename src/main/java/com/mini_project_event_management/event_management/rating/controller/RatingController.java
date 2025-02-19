package com.mini_project_event_management.event_management.rating.controller;

import com.mini_project_event_management.event_management.event.dto.EventDto;
import com.mini_project_event_management.event_management.rating.dto.RatingDto;
import com.mini_project_event_management.event_management.rating.dto.RatingRequestDto;
import com.mini_project_event_management.event_management.rating.service.RatingService;
import com.mini_project_event_management.event_management.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rating")
public class RatingController {

    private final RatingService ratingService;
    public RatingController(RatingService ratingService){
        this.ratingService = ratingService;
    }

    @PostMapping("/create-rating")
    public ResponseEntity<Response<RatingRequestDto>> createRating(@Validated @RequestBody RatingRequestDto ratingRequestDto){
        var createdRating = ratingService.createRating(ratingRequestDto);
        return Response.successfulResponse("Rating created successfully", createdRating);
    }

    @GetMapping("{eventId}")
    public ResponseEntity<Response<List<RatingDto>>> getRatingByEventId(@PathVariable Long eventId){
        var ratings = ratingService.getRatingByEventId(eventId);
        return Response.successfulResponse("Rating fetched successfully", ratings);
    }


}
