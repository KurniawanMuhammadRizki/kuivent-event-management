package com.mini_project_event_management.event_management.speakers.repository;

import com.mini_project_event_management.event_management.speakers.entity.Speakers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpeakersRepository extends JpaRepository<Speakers, Long> {
     List<Speakers> findAllByEventId(Long id);

     Speakers findBySlug(String slug);

     Boolean existsByName(String name);
}
