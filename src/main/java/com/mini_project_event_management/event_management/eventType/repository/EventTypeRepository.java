package com.mini_project_event_management.event_management.eventType.repository;

import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.eventType.entity.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTypeRepository extends JpaRepository<EventType, Integer> {
}
