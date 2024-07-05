package com.mini_project_event_management.event_management.category.controller;

import com.mini_project_event_management.event_management.category.dto.CategoryByEventIdDto;
import com.mini_project_event_management.event_management.category.dto.CategoryDto;
import com.mini_project_event_management.event_management.category.entity.Category;
import com.mini_project_event_management.event_management.category.service.CategoryService;
import com.mini_project_event_management.event_management.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @PostMapping
    public ResponseEntity<Response<String>> addCategory(@Validated @RequestBody CategoryDto categoryDto){
         categoryService.addCategory(categoryDto);
        return  Response.successfulResponse("Category added successfully" );
    }
    @GetMapping("{id}")
    public ResponseEntity<Response<CategoryDto>> getCategoryById(@PathVariable Long id ){
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        return Response.successfulResponse("Category Fetched", categoryDto);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<Response<List<CategoryDto>>> getListCategoryByEventId(@PathVariable Long id){
        List<CategoryDto> categories = categoryService.getCategoryByEventId(id);
        return Response.successfulResponse("Categories Fetched Successfully", categories);
    }

}
