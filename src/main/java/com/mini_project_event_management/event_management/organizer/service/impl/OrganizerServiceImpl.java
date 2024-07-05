package com.mini_project_event_management.event_management.organizer.service.impl;

import com.github.slugify.Slugify;
import com.mini_project_event_management.event_management.company.dto.RegisterCompanyRequestDto;
import com.mini_project_event_management.event_management.company.dto.RegisterCompanyResponseDto;
import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.organizer.dto.RegisterOrganizerRequestDto;
import com.mini_project_event_management.event_management.organizer.dto.RegisterOrganizerResponseDto;
import com.mini_project_event_management.event_management.organizer.entity.Organizer;
import com.mini_project_event_management.event_management.organizer.repository.OrganizerRepository;
import com.mini_project_event_management.event_management.organizer.service.OrganizerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrganizerServiceImpl implements OrganizerService {
    private final OrganizerRepository organizerRepository;
    private final PasswordEncoder passwordEncoder;

    public OrganizerServiceImpl(OrganizerRepository organizerRepository, PasswordEncoder passwordEncoder){
        this.organizerRepository = organizerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public RegisterOrganizerResponseDto register(RegisterOrganizerRequestDto registerDto) {

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
}
