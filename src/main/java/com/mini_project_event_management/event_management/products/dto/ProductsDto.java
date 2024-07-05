package com.mini_project_event_management.event_management.products.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
@Data
public class ProductsDto implements Serializable {
    @NotBlank(message = "Name id  is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    private Long companyId;
    private String imageUrl;

    private String slug;
}
