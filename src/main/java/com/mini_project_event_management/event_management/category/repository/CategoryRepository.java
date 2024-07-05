package com.mini_project_event_management.event_management.category.repository;

import com.mini_project_event_management.event_management.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByEventId(Long id);
}
