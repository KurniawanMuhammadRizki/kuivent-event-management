package com.mini_project_event_management.event_management.company.service.company.service;
// Generated by CodiumAI

import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.company.repository.CompanyRepository;
import com.mini_project_event_management.event_management.company.service.impl.CompanyServiceImpl;

import com.mini_project_event_management.event_management.exceptions.DataNotFoundException;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetcompanybyemailTest {


    // Successfully retrieve a company by a valid email
    @Test
    public void test_successfully_retrieve_company_by_valid_email() {
        CompanyRepository companyRepository = mock(CompanyRepository.class);
        CompanyServiceImpl companyService = new CompanyServiceImpl(companyRepository, null, null, null, null);
        String email = "test@example.com";
        Company company = new Company();
        company.setEmail(email);
        when(companyRepository.findByEmail(email)).thenReturn(Optional.of(company));
        Company result = companyService.getCompanyByEmail(email);
        assertNotNull(result);
        assertEquals(email, result.getEmail());
    }

    // Return the correct Company object when email exists
    @Test
    public void test_return_correct_company_object_when_email_exists() {
        CompanyRepository companyRepository = mock(CompanyRepository.class);
        CompanyServiceImpl companyService = new CompanyServiceImpl(companyRepository, null, null, null, null);
        String email = "test@example.com";
        Company expectedCompany = new Company();
        expectedCompany.setEmail(email);
        when(companyRepository.findByEmail(email)).thenReturn(Optional.of(expectedCompany));
        Company result = companyService.getCompanyByEmail(email);
        assertEquals(expectedCompany, result);
    }

    // Email does not exist in the database
    @Test
    public void test_email_does_not_exist_in_database() {
        CompanyRepository companyRepository = mock(CompanyRepository.class);
        CompanyServiceImpl companyService = new CompanyServiceImpl(companyRepository, null, null, null, null);
        String email = "nonexistent@example.com";
        when(companyRepository.findByEmail(email)).thenReturn(Optional.empty());
        assertThrows(DataNotFoundException.class, () -> companyService.getCompanyByEmail(email));
    }

    // Email is null or empty
    @Test
    public void test_email_is_null_or_empty() {
        CompanyRepository companyRepository = mock(CompanyRepository.class);
        CompanyServiceImpl companyService = new CompanyServiceImpl(companyRepository, null, null, null, null);
        assertThrows(DataNotFoundException.class, () -> companyService.getCompanyByEmail(null));
        assertThrows(DataNotFoundException.class, () -> companyService.getCompanyByEmail(""));
    }

    // Email format is invalid
    @Test
    public void test_email_format_is_invalid() {
        CompanyRepository companyRepository = mock(CompanyRepository.class);
        CompanyServiceImpl companyService = new CompanyServiceImpl(companyRepository, null, null, null, null);
        String invalidEmail = "invalid-email-format";
        when(companyRepository.findByEmail(invalidEmail)).thenReturn(Optional.empty());
        assertThrows(DataNotFoundException.class, () -> companyService.getCompanyByEmail(invalidEmail));
    }

}