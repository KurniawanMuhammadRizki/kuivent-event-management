package com.mini_project_event_management.event_management.rating.repository;

import com.mini_project_event_management.event_management.rating.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
     List<Rating> findAllByEventId(Long id);
}
