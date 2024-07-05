package com.mini_project_event_management.event_management.eventCompany.repository;

import com.mini_project_event_management.event_management.eventCompany.entity.EventCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventCompanyRepository  extends JpaRepository<EventCompany, Long> {
    List<EventCompany> findAllByEventId(Long id);
    Boolean existsByCompanyId(Long id);
}
