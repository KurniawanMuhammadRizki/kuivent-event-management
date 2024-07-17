package com.mini_project_event_management.event_management.speakers.service.impl;

import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.event.service.EventService;
import com.mini_project_event_management.event_management.exceptions.AlreadyExistException;
import com.mini_project_event_management.event_management.exceptions.DataNotFoundException;
import com.mini_project_event_management.event_management.helpers.SlugifyHelper;
import com.mini_project_event_management.event_management.speakers.dto.SpeakerDto;
import com.mini_project_event_management.event_management.speakers.entity.Speakers;
import com.mini_project_event_management.event_management.speakers.repository.SpeakersRepository;
import com.mini_project_event_management.event_management.speakers.service.SpeakersService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpeakerServiceImpl implements SpeakersService {

     private final SpeakersRepository speakersRepository;
     private final EventService eventService;


     public SpeakerServiceImpl(SpeakersRepository speakersRepository, EventService eventService) {
          this.speakersRepository = speakersRepository;
          this.eventService = eventService;
     }

     Instant now = Instant.now();

     @Override
     public SpeakerDto addSpeaker(SpeakerDto speakerDto) {
          Boolean isNameExist = speakersRepository.existsByName(speakerDto.getName());
          if (isNameExist) {
               throw new AlreadyExistException("Speaker name already exist");
          }
          Event event = eventService.getEventById(speakerDto.getEventId());
          String slug = SlugifyHelper.slugify(speakerDto.getName());
          Speakers speakers = new Speakers();
          speakers.setName(speakerDto.getName());
          speakers.setCompanyName(speakerDto.getCompanyName());
          speakers.setPosition(speakerDto.getPosition());
          speakers.setAbout(speakerDto.getAbout());
          speakers.setCreatedAt(now);
          speakers.setUpdatedAt(now);
          speakers.setSlug(slug);
          speakers.setEvent(event);
          speakersRepository.save(speakers);
          return convertToDto(speakers);
     }

     @Override
     @Cacheable(value = "getSpeakerById", key = "#id")
     public Speakers getSpeakerById(Long id) {
          Optional<Speakers> speaker = speakersRepository.findById(id);
          if (speaker.isEmpty()) {
               throw new DataNotFoundException("Speaker not found");
          }
          return speaker.orElse(null);
     }

     @Override
     @Cacheable(value = "getSpeakersBySlug", key = "#slug")
     public SpeakerDto getSpeakersBySlug(String slug) {
          Optional<Speakers> speakers = Optional.ofNullable(speakersRepository.findBySlug(slug));
          if (speakers.isEmpty()) {
               throw new DataNotFoundException("Speaker not found");
          }
          return speakers.get().toSpeakerDto();
     }

     @Override
     public List<SpeakerDto> getSpeakersByEventId(Long id) {
          List<Speakers> speakers = speakersRepository.findAllByEventId(id);
          if (speakers == null || speakers.isEmpty()) {
               throw new DataNotFoundException("Speaker not found");
          }
          return speakers.stream().map(this::convertToDto).collect(Collectors.toList());
     }


     private SpeakerDto convertToDto(Speakers speakers) {
          SpeakerDto dto = new SpeakerDto();
          dto.setName(speakers.getName());
          dto.setCompanyName(speakers.getCompanyName());
          dto.setProfileImgUrl(speakers.getProfileImageUrl());
          dto.setPosition(speakers.getPosition());
          dto.setAbout(speakers.getAbout());
          return dto;
     }


}
