package com.mini_project_event_management.event_management.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mini_project_event_management.event_management.event.entity.Event;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
@Data
public class EventDto {
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "Address cannot be empty")
    private String address;
    @NotBlank(message = "City cannot be empty")
    private String city;

    private String websiteUrl;
    private String imageUrl;
    @NotBlank(message = "Description cannot be empty")
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date dateStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date dateEnd;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Date hourStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Date hourEnd;

    @Min(value = 0, message = "Capacity must be zero or positive")
    private int capacity;

    @NotNull(message = "Event type cannot be null")
    private Long eventTypeId;

}
