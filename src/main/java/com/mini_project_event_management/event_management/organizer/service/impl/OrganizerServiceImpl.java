package com.mini_project_event_management.event_management.organizer.service.impl;

import com.github.slugify.Slugify;
import com.mini_project_event_management.event_management.company.dto.RegisterCompanyRequestDto;
import com.mini_project_event_management.event_management.company.dto.RegisterCompanyResponseDto;
import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.exceptions.AlreadyExistException;
import com.mini_project_event_management.event_management.exceptions.DataNotFoundException;
import com.mini_project_event_management.event_management.helpers.CurrentUser;
import com.mini_project_event_management.event_management.organizer.dto.OrganizerDto;
import com.mini_project_event_management.event_management.organizer.dto.RegisterOrganizerRequestDto;
import com.mini_project_event_management.event_management.organizer.dto.RegisterOrganizerResponseDto;
import com.mini_project_event_management.event_management.organizer.entity.Organizer;
import com.mini_project_event_management.event_management.organizer.repository.OrganizerRepository;
import com.mini_project_event_management.event_management.organizer.service.OrganizerService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
public class OrganizerServiceImpl implements OrganizerService {
     private final OrganizerRepository organizerRepository;
     private final PasswordEncoder passwordEncoder;
     private final CurrentUser currentUser;

     public OrganizerServiceImpl(OrganizerRepository organizerRepository, PasswordEncoder passwordEncoder, @Lazy CurrentUser currentUser) {
          this.organizerRepository = organizerRepository;
          this.passwordEncoder = passwordEncoder;
          this.currentUser = currentUser;
     }

     @Override
     @Transactional
     public RegisterOrganizerResponseDto register(RegisterOrganizerRequestDto registerDto) {
          Boolean isEmailExist = organizerRepository.existsByEmail(registerDto.getEmail());
          Boolean isNameExist = organizerRepository.existsByName(registerDto.getName());
          if (isEmailExist) {
               throw new AlreadyExistException("Email already exixst");
          }
          if (isNameExist) {
               throw new AlreadyExistException("Name already exist");
          }
          Organizer organizer = registerDto.toEntity();
          var password = passwordEncoder.encode(organizer.getPassword());
          Instant now = Instant.now();
          organizer.setPassword(password);
          organizer.setCreatedAt(now);
          organizer.setUpdatedAt(now);
          organizer.setProfileUrl(" ");

          var organizerRegistered = organizerRepository.save(organizer);

          RegisterOrganizerResponseDto registerOrganizerResponseDto = new RegisterOrganizerResponseDto();
          registerOrganizerResponseDto.setId(organizerRegistered.getId());
          registerOrganizerResponseDto.setAddress(organizerRegistered.getAddress());
          registerOrganizerResponseDto.setName(organizerRegistered.getName());
          registerOrganizerResponseDto.setCity(organizerRegistered.getCity());
          registerOrganizerResponseDto.setEmail(organizerRegistered.getEmail());
          registerOrganizerResponseDto.setWebsiteUrl(organizerRegistered.getWebsiteUrl());
          registerOrganizerResponseDto.setPhoneNumber(organizerRegistered.getPhoneNumber());
          return registerOrganizerResponseDto;
     }

     @Override
     @Cacheable(value = "getOrganizerById", key = "#id")
     public Organizer getOrganizerById(Long id) {
          Optional<Organizer> organizer = organizerRepository.findById(id);

          if (organizer.isEmpty()) {
               throw new DataNotFoundException("Organizer not found");
          }

          return organizer.orElse(null);
     }

     @Override
     public OrganizerDto getOrganizer() {
          Long organizerId = currentUser.getAuthorizedOrganizerId();
          Organizer organizer = getOrganizerById(organizerId);
          return organizer.toOrganizerDto(organizer);
     }

     @Override
     @Cacheable(value = "getOrganizerByEmail", key = "#email")
     public Organizer getOrganizerByEmail(String email) {
          Optional<Organizer> organizer = organizerRepository.findByEmail(email);
          if (organizer.isEmpty()) {
               throw new DataNotFoundException("Organizer not found");
          }
          return organizer.orElse(null);
     }
}
