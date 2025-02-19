package com.mini_project_event_management.event_management.company.dto;

import com.mini_project_event_management.event_management.company.entity.Company;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterCompanyResponseDto {
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

    public Company toEntity(){
        Company company = new Company();
        company.setName(name);
        company.setEmail(email);
        company.setPhoneNumber(phoneNumber);
        company.setCity(city);
        company.setAddress(address);
        company.setWebsiteUrl(websiteUrl);
        return company;
    }
}
