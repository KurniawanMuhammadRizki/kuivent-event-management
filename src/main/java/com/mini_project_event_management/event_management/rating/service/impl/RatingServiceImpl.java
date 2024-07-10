package com.mini_project_event_management.event_management.rating.service.impl;

import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.event.service.EventService;
import com.mini_project_event_management.event_management.exceptions.DataNotFoundException;
import com.mini_project_event_management.event_management.rating.dto.RatingDto;
import com.mini_project_event_management.event_management.rating.dto.RatingRequestDto;
import com.mini_project_event_management.event_management.rating.entity.Rating;
import com.mini_project_event_management.event_management.rating.repository.RatingRepository;
import com.mini_project_event_management.event_management.rating.service.RatingService;
import com.mini_project_event_management.event_management.users.entity.Users;
import com.mini_project_event_management.event_management.users.service.UsersService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final EventService eventService;
    private final UsersService usersService;


    public RatingServiceImpl(RatingRepository ratingRepository, EventService eventService, UsersService usersService){
        this.ratingRepository = ratingRepository;
        this.eventService = eventService;
        this.usersService = usersService;
    }

    Rating toRating(RatingRequestDto ratingRequestDto){
        Rating rating = new Rating();
        Instant now = Instant.now();
        Event event = eventService.getEventById(ratingRequestDto.getEventId());
        Users user = usersService.getUserById(ratingRequestDto.getUserId());
       rating.setUsers(user);
        rating.setEvent(event);
        rating.setRating(ratingRequestDto.getRating());
        rating.setReview(ratingRequestDto.getReview());
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

    @Override
     public List<RatingDto> getRatingByEventId(Long eventId){
         List<Rating> ratings = ratingRepository.findAllByEventId(eventId);
         if(ratings.isEmpty() || ratings == null){
              throw new DataNotFoundException("Categories not found");
         }
        return ratings.stream().map(Rating::toRatingDto).collect(Collectors.toList());
    }

}
