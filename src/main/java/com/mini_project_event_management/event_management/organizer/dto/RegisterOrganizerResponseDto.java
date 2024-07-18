package com.mini_project_event_management.event_management.organizer.dto;

import com.mini_project_event_management.event_management.organizer.entity.Organizer;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterOrganizerResponseDto {
     private Long id;

     @NotBlank(message = "Name is Required")
     private String name;

     @NotBlank(message = "Email is Required")
     private String email;

     @NotBlank(message = "Phone number is Required")
     private String phoneNumber;

     @NotBlank(message = "Address number is Required")
     private String address;

     @NotBlank(message = "City number is Required")
     private String city;

     @NotBlank(message = "Website url number is Required")
     private String websiteUrl;

     public Organizer toEntity() {
          Organizer organizer = new Organizer();
          organizer.setName(name);
          organizer.setEmail(email);
          organizer.setPhoneNumber(phoneNumber);
          organizer.setAddress(address);
          organizer.setCity(city);
          organizer.setWebsiteUrl(websiteUrl);
          return organizer;
     }
}
