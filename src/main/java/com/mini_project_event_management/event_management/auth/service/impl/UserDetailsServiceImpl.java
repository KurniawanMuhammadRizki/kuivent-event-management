package com.mini_project_event_management.event_management.auth.service.impl;

import com.mini_project_event_management.event_management.auth.entity.CompanyAuth;
import com.mini_project_event_management.event_management.auth.entity.OrganizerAuth;
import com.mini_project_event_management.event_management.auth.entity.UsersAuth;
import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.company.repository.CompanyRepository;
import com.mini_project_event_management.event_management.organizer.entity.Organizer;
import com.mini_project_event_management.event_management.organizer.repository.OrganizerRepository;
import com.mini_project_event_management.event_management.users.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final CompanyRepository companyRepository;
    private final OrganizerRepository organizerRepository;

    private final UsersRepository usersRepository;

    public UserDetailsServiceImpl(CompanyRepository companyRepository, OrganizerRepository organizerRepository, UsersRepository usersRepository) {
        this.companyRepository = companyRepository;
        this.organizerRepository = organizerRepository;
        this.usersRepository = usersRepository;
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        var companyData = companyRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("company not found"));
//        return new CompanyAuth(companyData);
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var companyAuthData = companyRepository.findByEmail(email).orElse(null);
        var organizerAuthData = organizerRepository.findByEmail(email).orElse(null);
        var usersAuthData = usersRepository.findByEmail(email).orElse(null);

//        if(companyAuthData != null){
//            return new CompanyAuth(companyAuthData);
//        } else if(organizerAuthData != null){
//            return new OrganizerAuth(organizerAuthData);
//        } else {
//            throw new UsernameNotFoundException("email not found");
//        }
        if (organizerAuthData != null) {
            return new OrganizerAuth(organizerAuthData);
        } else if (companyAuthData != null) {
            return new CompanyAuth(companyAuthData);
        } else if (usersAuthData != null) {
            return new UsersAuth(usersAuthData);
        } else {
            throw new UsernameNotFoundException("email not found");
        }
    }


}
