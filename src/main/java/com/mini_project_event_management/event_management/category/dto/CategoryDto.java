package com.mini_project_event_management.event_management.category.dto;

import com.mini_project_event_management.event_management.block.dto.BlockDto;
import com.mini_project_event_management.event_management.block.entity.Block;
import com.mini_project_event_management.event_management.category.entity.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<BlockDto> blocks;


    public CategoryDto toCategoryDto (Category category){
     CategoryDto categoryDto = new CategoryDto();
     categoryDto.setQuota(category.getQuota());
     categoryDto.setId(category.getId());
     categoryDto.setName(category.getName());
     categoryDto.setPrice(category.getPrice());
     categoryDto.setDescription(category.getDescription());
     categoryDto.setDescriptionDetail(category.getDescriptionDetail());
     List<BlockDto> blocks = category.getBlocks().stream()
             .map(Block::toBlockDto)
             .collect(Collectors.toList());

     categoryDto.setBlocks(blocks);
    return categoryDto;
    }

    public Category toEntity(){
     Category category = new Category();
     category.setId(id);
     category.setPrice(price);
     category.setDescription(description);
     category.setDescriptionDetail(descriptionDetail);
     category.setName(name);
     category.setQuota(quota);
     return category;
    }
}
