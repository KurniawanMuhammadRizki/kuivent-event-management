package com.mini_project_event_management.event_management.rating.service;

import com.mini_project_event_management.event_management.rating.dto.RatingDto;
import com.mini_project_event_management.event_management.rating.dto.RatingRequestDto;

import java.util.List;

public interface RatingService {
    RatingRequestDto createRating(RatingRequestDto ratingRequestDto);
    List<RatingDto> getRatingByEventId(Long eventId);
}
