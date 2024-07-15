package com.mini_project_event_management.event_management.tickets.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddTicketDto {
     @NotNull(message = "Event  cannot be null")
     private Long eventId;

     @NotNull(message = "User  cannot be null")
     private Long userId;

     @NotNull(message = "Category  cannot be null")
     private Long categoryId;

     @NotNull(message = "Block  cannot be null")
     private Long blockId;

}
