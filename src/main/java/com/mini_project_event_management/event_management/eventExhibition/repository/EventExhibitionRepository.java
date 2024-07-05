package com.mini_project_event_management.event_management.eventExhibition.repository;

import com.mini_project_event_management.event_management.eventExhibition.entity.EventExhibition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventExhibitionRepository extends JpaRepository<EventExhibition, Long> {
}
