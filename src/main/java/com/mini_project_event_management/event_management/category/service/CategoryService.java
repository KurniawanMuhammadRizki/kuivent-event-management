package com.mini_project_event_management.event_management.category.service;

import com.mini_project_event_management.event_management.category.dto.CategoryDto;
import com.mini_project_event_management.event_management.category.entity.Category;

import java.util.List;

public interface CategoryService {
    void addCategory(CategoryDto categoryDto);
    CategoryDto getCategoryById(Long id);
    List<CategoryDto> getCategoryByEventId(Long id);
}
