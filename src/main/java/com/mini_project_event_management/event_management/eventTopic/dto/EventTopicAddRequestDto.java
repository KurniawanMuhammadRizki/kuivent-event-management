package com.mini_project_event_management.event_management.eventTopic.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EventTopicAddRequestDto {
    @NotNull(message = "Event id  is required")
    private Long eventId;
    @NotNull(message = "Topic id  is required")
    private Long topicId;
}
