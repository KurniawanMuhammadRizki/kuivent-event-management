package com.mini_project_event_management.event_management.topics.dto;

import com.mini_project_event_management.event_management.topics.entity.Topic;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TopicDto {
    @NotBlank(message = "Name  is required")
    private String name;
    @NotBlank(message = "Image url  is required")
    private String imgUrl;

    public TopicDto toTopicDto(Topic topic){
        TopicDto topicDto = new TopicDto();
        topicDto.setImgUrl(topic.getImgUrl());
        topicDto.setName(topic.getName());
        return topicDto;
    }
}
