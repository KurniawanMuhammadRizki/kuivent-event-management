package com.mini_project_event_management.event_management.category.service.impl;

import com.mini_project_event_management.event_management.category.dto.CategoryDto;
import com.mini_project_event_management.event_management.category.entity.Category;
import com.mini_project_event_management.event_management.category.repository.CategoryRepository;
import com.mini_project_event_management.event_management.category.service.CategoryService;
import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.event.service.EventService;
import com.mini_project_event_management.event_management.exceptions.DataNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final EventService eventService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, EventService eventService){
        this.categoryRepository = categoryRepository;
        this.eventService = eventService;
    }

    @Override
    public void addCategory(CategoryDto categoryDto){
        Event event = eventService.getEventById(categoryDto.getEventId());
        Instant now = Instant.now();
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setEvent(event);
        category.setPrice(categoryDto.getPrice());
        category.setDescription(categoryDto.getDescription());
        category.setDescriptionDetail(categoryDto.getDescriptionDetail());
        category.setQuota(categoryDto.getQuota());
        category.setCreatedAt(now);
        category.setUpdatedAt(now);

        categoryRepository.save(category);
    }

    @Override
    @Cacheable(value = "getCategoryById", key = "#id")
    public CategoryDto getCategoryById(Long id){
        Optional<Category>  category = categoryRepository.findById(id);

        if(category.isEmpty()){
            throw new DataNotFoundException("Category not found");
        }

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.get().getId());
        categoryDto.setName(category.get().getName());
        categoryDto.setPrice(category.get().getPrice());
        categoryDto.setQuota(category.get().getQuota());
        categoryDto.setDescription(category.get().getDescription());
        categoryDto.setDescriptionDetail(category.get().getDescriptionDetail());
        categoryDto.setEventId(category.get().getEvent().getId());
        return categoryDto;
    }

    @Override
    public List<CategoryDto> getCategoryByEventId(Long id){
        List<Category> categories = categoryRepository.findAllByEventId(id);
        if (categories == null || categories.isEmpty()) {
            throw new DataNotFoundException("Categories not found");
        }
        return categories.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CategoryDto convertToDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setEventId(category.getEvent().getId());
        dto.setPrice(category.getPrice());
        dto.setQuota(category.getQuota());
        dto.setDescription(category.getDescription());
        dto.setDescriptionDetail(category.getDescriptionDetail());
        return dto;
    }


}
