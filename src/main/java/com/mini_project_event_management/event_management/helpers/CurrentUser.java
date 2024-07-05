package com.mini_project_event_management.event_management.helpers;

import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.company.repository.CompanyRepository;
import com.mini_project_event_management.event_management.company.service.CompanyService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser  {
    private final CompanyService companyService;
    public CurrentUser(CompanyService companyService){
        this.companyService = companyService;
    }

    public  Long getAuthorizedCompanyId(){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        String requesterEmail = auth.getName();
        Company company = companyService.getCompanyByEmail(requesterEmail);
        return company.getId();
    }

}
