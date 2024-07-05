package com.mini_project_event_management.event_management.category.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDto implements Serializable {
   private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Event id cannot be empty")
    private Long eventId;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    private String descriptionDetail;

    @Min(value = 0, message = "Quota must be zero or positive")
    private int quota;

    @Min(value = 0, message = "Price must be zero or positive")
    private int price;
}
