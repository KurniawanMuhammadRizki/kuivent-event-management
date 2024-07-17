package com.mini_project_event_management.event_management.users.service;

import com.mini_project_event_management.event_management.users.dto.RegisterUserRequestDto;
import com.mini_project_event_management.event_management.users.dto.RegisterUserResponseDto;
import com.mini_project_event_management.event_management.users.dto.UsersDto;
import com.mini_project_event_management.event_management.users.entity.Users;
import lombok.Data;


public interface UsersService {
     RegisterUserResponseDto register(RegisterUserRequestDto registerUserRequestDto);

     Users getUserById(Long id);

     Users getUserByEmail(String email);

     UsersDto getUsers();
}
