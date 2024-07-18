package com.mini_project_event_management.event_management.users.controller;

import com.mini_project_event_management.event_management.responses.Response;
import com.mini_project_event_management.event_management.users.dto.RegisterUserRequestDto;
import com.mini_project_event_management.event_management.users.dto.RegisterUserResponseDto;
import com.mini_project_event_management.event_management.users.dto.UsersDto;
import com.mini_project_event_management.event_management.users.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
     private final UsersService usersService;

     public UsersController(UsersService usersService) {
          this.usersService = usersService;
     }

     @PostMapping("/register")
     public ResponseEntity<Response<RegisterUserResponseDto>> register(@Validated @RequestBody RegisterUserRequestDto registerUserRequestDto) {
          var userRegistered = usersService.register(registerUserRequestDto);
          return Response.successfulResponse("User registered successfully", userRegistered);
     }

     @GetMapping
     public ResponseEntity<Response<UsersDto>> getUsers() {
          UsersDto usersDto = usersService.getUsers();
          return Response.successfulResponse("User fetched successfully", usersDto);
     }

}
