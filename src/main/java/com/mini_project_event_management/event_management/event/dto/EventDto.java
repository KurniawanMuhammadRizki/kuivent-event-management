package com.mini_project_event_management.event_management.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mini_project_event_management.event_management.category.dto.CategoryDto;
import com.mini_project_event_management.event_management.category.entity.Category;
import com.mini_project_event_management.event_management.company.dto.CompanyDto;
import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.eventType.dto.EventTypeDto;
import com.mini_project_event_management.event_management.eventType.entity.EventType;
import com.mini_project_event_management.event_management.rating.dto.RatingDto;
import com.mini_project_event_management.event_management.speakers.dto.SpeakerDto;
import com.mini_project_event_management.event_management.topics.dto.TopicDto;
import com.mini_project_event_management.event_management.topics.entity.Topic;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

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

    private String descriptionDetail;

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

    private List<TopicDto> topics;
   private List<CategoryDto> categories;
   private List<CompanyDto> companies;
   private List<SpeakerDto> speakers;
   private List<RatingDto> ratings;
   private EventTypeDto eventType;


}
