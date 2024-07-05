package com.mini_project_event_management.event_management.event.repository;

import com.mini_project_event_management.event_management.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository  extends JpaRepository<Event, Long> {
}
