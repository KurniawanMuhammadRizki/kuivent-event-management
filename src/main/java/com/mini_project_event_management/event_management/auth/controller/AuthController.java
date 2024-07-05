package com.mini_project_event_management.event_management.auth.controller;

import com.github.slugify.Slugify;
import com.mini_project_event_management.event_management.auth.dto.LoginRequestDto;
import com.mini_project_event_management.event_management.auth.dto.LoginResponseDto;
import com.mini_project_event_management.event_management.auth.entity.CompanyAuth;
import com.mini_project_event_management.event_management.auth.entity.OrganizerAuth;
import com.mini_project_event_management.event_management.auth.service.AuthService;
import com.mini_project_event_management.event_management.auth.service.impl.UserDetailsServiceImpl;
import com.mini_project_event_management.event_management.helpers.Claims;
import com.mini_project_event_management.event_management.helpers.SlugifyHelper;
import jakarta.servlet.http.Cookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@Validated
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager){
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

//    @PostMapping
//    public ResponseEntity<?> login(@RequestBody LoginRequestDto userLogin){
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        CompanyAuth userDetails = (CompanyAuth) authentication.getPrincipal();
//        String token = authService.generateToken(authentication);
//
//        LoginResponseDto response = new LoginResponseDto();
//        response.setMessage("User logged in successfully");
//        response.setToken(token);
//
//        Cookie cookie = new Cookie("sid", token);
//        HttpHeaders header = new HttpHeaders();
//        header.add("Set-Cookie", cookie.getName() + "=" + cookie.getValue() + "; Path=/; HttpOnly");
//
//        return ResponseEntity.status(HttpStatus.OK).headers(header).body(response);
//    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequestDto userLogin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = authService.generateToken(authentication);

        String role = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        LoginResponseDto response = new LoginResponseDto();
        response.setMessage("User logged in successfully");
        response.setToken(token);
        response.setRole(role);


//        SecurityContext ctx = SecurityContextHolder.getContext();
//        Authentication auth = ctx.getAuthentication();
//
//
//        LoginResponseDto response = new LoginResponseDto();
//        response.setMessage("User logged in successfully");
//        response.setToken(token);
//        response.setRole(role);

        Cookie cookie = new Cookie("sid", token);
        HttpHeaders header = new HttpHeaders();
        header.add("Set-Cookie", cookie.getName() + "=" + cookie.getValue() + "; Path=/; HttpOnly");

        return ResponseEntity.status(HttpStatus.OK).headers(header).body(response);
    }

//    @PostMapping
//    public ResponseEntity<?> login(@RequestBody LoginRequestDto userLogin){
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        OrganizerAuth userDetails = (OrganizerAuth) authentication.getPrincipal();
//        String token = authService.generateToken(authentication);
//
//        LoginResponseDto response = new LoginResponseDto();
//        response.setMessage("User logged in successfully");
//        response.setToken(token);
//
//        Cookie cookie = new Cookie("sid", token);
//        HttpHeaders header = new HttpHeaders();
//        header.add("Set-Cookie", cookie.getName() + "=" + cookie.getValue() + "; Path=/; HttpOnly");
//
//        return ResponseEntity.status(HttpStatus.OK).headers(header).body(response);
//    }

}
