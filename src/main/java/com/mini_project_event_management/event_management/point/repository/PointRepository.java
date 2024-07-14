package com.mini_project_event_management.event_management.point.repository;

import com.mini_project_event_management.event_management.point.entity.Point;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PointRepository extends JpaRepository<Point, Long> {
     @Query("SELECT SUM(p.point) FROM Point p WHERE p.company.id = :companyId AND p.deletedAt IS NULL AND (p.expiredAt IS NULL OR p.expiredAt > CURRENT_TIMESTAMP)")
     Integer sumPointsByCompanyId(@Param("companyId") Long companyId);

     @Modifying
     @Query("UPDATE Point p SET p.deletedAt = CURRENT_TIMESTAMP WHERE p.company.id = :companyId AND p.deletedAt IS NULL")
     int softDeletePointsByCompanyId(@Param("companyId") Long companyId);
}
