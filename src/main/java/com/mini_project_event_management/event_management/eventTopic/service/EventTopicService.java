package com.mini_project_event_management.event_management.eventTopic.service;

import com.mini_project_event_management.event_management.eventTopic.dto.EventTopicAddRequestDto;
import com.mini_project_event_management.event_management.eventTopic.dto.EventTopicResponseDto;
import com.mini_project_event_management.event_management.topics.dto.TopicDto;

import java.util.List;

public interface EventTopicService {
    void addEventTopic(EventTopicAddRequestDto topicAddRequestDto);
    List<EventTopicResponseDto> getEventTopicByEventId(Long id);
}
