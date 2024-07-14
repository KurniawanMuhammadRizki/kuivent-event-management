package com.mini_project_event_management.event_management.event.repository;

import com.mini_project_event_management.event_management.event.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository  extends JpaRepository<Event, Long> {
     Optional<Event> findBySlug(String slug);
     Page<Event> findByNameContainingIgnoreCaseOrCityContainingIgnoreCase(
             String name, String city, Pageable pageable);
}
