package com.mini_project_event_management.event_management.organizer.repository;

import com.mini_project_event_management.event_management.organizer.entity.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
    Optional<Organizer> findByEmail(String email);
}
