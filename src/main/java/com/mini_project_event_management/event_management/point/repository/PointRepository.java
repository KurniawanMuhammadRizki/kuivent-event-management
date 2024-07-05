package com.mini_project_event_management.event_management.point.repository;

import com.mini_project_event_management.event_management.point.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
}
