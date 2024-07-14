package com.mini_project_event_management.event_management.block.dto;

import com.mini_project_event_management.event_management.block.entity.Block;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class BlockDto implements Serializable {
     @NotBlank(message = "Name cannot be empty")
     private String name;

     @NotNull(message = "Category id cannot be empty")
     private Long categoryId;

     @NotNull(message = "IsBooked cannot be empty")
     private boolean isBooked;

     public Block toEntity(){
          Block block = new Block();
          block.setName(this.name);
          block.setBooked(this.isBooked);
          return block;
     }
}
