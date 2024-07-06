package com.mini_project_event_management.event_management.helpers;

import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.company.repository.CompanyRepository;
import com.mini_project_event_management.event_management.company.service.CompanyService;
import com.mini_project_event_management.event_management.organizer.entity.Organizer;
import com.mini_project_event_management.event_management.organizer.service.OrganizerService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser  {
    private final CompanyService companyService;
    private final OrganizerService organizerService;
    public CurrentUser(CompanyService companyService, OrganizerService organizerService){
        this.companyService = companyService;
        this.organizerService = organizerService;
    }

    public  Long getAuthorizedCompanyId(){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        String requesterEmail = auth.getName();
        Company company = companyService.getCompanyByEmail(requesterEmail);
        return company.getId();
    }

    public  Long getAuthorizedOrganizerId(){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        String requesterEmail = auth.getName();
        Organizer organizer = organizerService.getOrganizerByEmail(requesterEmail);
        return organizer.getId();
    }

}
