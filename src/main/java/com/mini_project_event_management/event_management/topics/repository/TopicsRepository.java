package com.mini_project_event_management.event_management.topics.repository;

import com.mini_project_event_management.event_management.topics.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicsRepository extends JpaRepository<Topic, Long> {
    Topic findByName(String name);
}
