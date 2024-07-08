package com.mini_project_event_management.event_management.company.controller;

import com.mini_project_event_management.event_management.company.dto.CompanyDto;
import com.mini_project_event_management.event_management.company.dto.RegisterCompanyRequestDto;
import com.mini_project_event_management.event_management.company.dto.RegisterCompanyResponseDto;
import com.mini_project_event_management.event_management.company.service.CompanyService;
import com.mini_project_event_management.event_management.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @PostMapping("/register")
    public ResponseEntity<Response<RegisterCompanyResponseDto>> register( @Validated  @RequestBody RegisterCompanyRequestDto registerDto) {
        var companyRegistered = companyService.register(registerDto);
        return Response.successfulResponse("Company registered successfully", companyRegistered);
    }

    @GetMapping
    public ResponseEntity<Response<List<CompanyDto>>> getAllCompany(){
        var companies = companyService.getAllCompany();
        return Response.successfulResponse("Company fetched successfully", companies);
    }


}
