package com.mini_project_event_management.event_management.company.service;

import com.mini_project_event_management.event_management.company.dto.RegisterCompanyRequestDto;
import com.mini_project_event_management.event_management.company.dto.RegisterCompanyResponseDto;
import com.mini_project_event_management.event_management.company.entity.Company;

public interface CompanyService {
    RegisterCompanyResponseDto register(RegisterCompanyRequestDto registerDto);
    Company getCompanyByEmail(String email);
    Company getCompanyById(Long id);
}
