package com.mini_project_event_management.event_management.eventTopic.dto;

import com.mini_project_event_management.event_management.topics.dto.TopicDto;
import lombok.Data;

@Data
public class EventTopicResponseDto {
    private Long eventId;
    private TopicDto topic;
}
