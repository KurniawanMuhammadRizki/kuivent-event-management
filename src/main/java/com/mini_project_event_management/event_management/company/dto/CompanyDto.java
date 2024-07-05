package com.mini_project_event_management.event_management.company.dto;

import com.mini_project_event_management.event_management.company.entity.Company;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompanyDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String websiteUrl;
    private String profileUrl;
    private String about;
    private String slug;

    public CompanyDto toCompanyDto(Company company){
        CompanyDto companyDto = new CompanyDto();
        companyDto.id = company.getId();
        companyDto.name = company.getName();
        companyDto.email = company.getEmail();
        companyDto.phoneNumber = company.getPhoneNumber();
        companyDto.address = company.getAddress();
        companyDto.city = company.getCity();
        companyDto.websiteUrl = company.getWebsiteUrl();
        companyDto.profileUrl = company.getProfileUrl();
        companyDto.about = company.getAbout();
        companyDto.slug = company.getSlug();
    return companyDto;
    }
}
