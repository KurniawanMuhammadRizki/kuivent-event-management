package com.mini_project_event_management.event_management.topics.service;

import com.mini_project_event_management.event_management.topics.dto.TopicDto;
import com.mini_project_event_management.event_management.topics.entity.Topic;

import java.util.List;

public interface TopicsService {
    TopicDto addTopic(TopicDto topicDto);

    List<TopicDto> getAllTopics();

    Topic getTopicById(Long id);
}
