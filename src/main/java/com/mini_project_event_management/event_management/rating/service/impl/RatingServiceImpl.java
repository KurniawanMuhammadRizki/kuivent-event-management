package com.mini_project_event_management.event_management.rating.service.impl;

import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.event.service.EventService;
import com.mini_project_event_management.event_management.rating.dto.RatingRequestDto;
import com.mini_project_event_management.event_management.rating.entity.Rating;
import com.mini_project_event_management.event_management.rating.repository.RatingRepository;
import com.mini_project_event_management.event_management.rating.service.RatingService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final EventService eventService;

    public RatingServiceImpl(RatingRepository ratingRepository, EventService eventService){
        this.ratingRepository = ratingRepository;
        this.eventService = eventService;
    }

    Rating toRating(RatingRequestDto ratingRequestDto){
        Rating rating = new Rating();
        Instant now = Instant.now();
        Event event = eventService.getEventById(ratingRequestDto.getEventId());
        rating.setUserId(ratingRequestDto.getUserId());
        rating.setEvent(event);
        rating.setRating(ratingRequestDto.getRating());
        rating.setReview(event.getName());
        rating.setCreatedAt(now);
        rating.setUpdatedAt(now);

        return rating;
    }

    @Override
    public RatingRequestDto createRating(RatingRequestDto ratingRequestDto){
        Rating rating = toRating(ratingRequestDto);
        ratingRepository.save(rating);
        return ratingRequestDto;
    }

}
