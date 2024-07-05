package com.mini_project_event_management.event_management.speakers.service;

import com.mini_project_event_management.event_management.speakers.dto.SpeakerDto;
import com.mini_project_event_management.event_management.speakers.entity.Speakers;

import java.util.List;

public interface SpeakersService {
    SpeakerDto addSpeaker(SpeakerDto speakerDto);

    Speakers getSpeakerById(Long id);
  List<SpeakerDto> getSpeakersByEventId(Long id);
}
