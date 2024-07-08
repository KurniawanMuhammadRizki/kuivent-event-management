package com.mini_project_event_management.event_management.speakers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class SpeakerDto implements Serializable {
    @NotBlank(message = "Name id  is required")
    private String name;
    private String profileImgUrl;
    private String position;
    private String about;
    private String companyName;
    @NotNull(message = "Event id  is required")
    private Long eventId;
    private String slug;
}
