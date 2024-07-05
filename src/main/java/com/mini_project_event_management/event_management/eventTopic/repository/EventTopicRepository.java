package com.mini_project_event_management.event_management.eventTopic.repository;

import com.mini_project_event_management.event_management.eventTopic.entity.EventTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventTopicRepository extends JpaRepository<EventTopic , Long> {
    List<EventTopic> findAllByEventId(long id);
    Boolean existsByTopicId(Long id);
}
