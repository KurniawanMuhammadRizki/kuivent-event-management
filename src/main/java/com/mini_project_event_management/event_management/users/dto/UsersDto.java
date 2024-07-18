package com.mini_project_event_management.event_management.users.dto;

import lombok.Data;

@Data
public class UsersDto {
     private Long id;
     private String firstName;
     private String lastName;
     private String address;
     private String city;
     private String email;
     private String about;
     private String profileImgUrl;
     private String phoneNumber;
     private String slug;
}
