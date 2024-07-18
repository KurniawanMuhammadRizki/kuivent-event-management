package com.mini_project_event_management.event_management.company.repository;

import com.mini_project_event_management.event_management.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByEmail(String email);
    Optional<Company> findBySlug(String slug);

    Boolean existsByName(String name);
    Boolean existsByEmail(String email);
}
