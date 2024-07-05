package com.mini_project_event_management.event_management.auth.entity;

import com.mini_project_event_management.event_management.company.entity.Company;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class CompanyAuth extends Company implements UserDetails {

    private final Company company;

    public CompanyAuth(Company company){
        this.company = company;
    }

    @Override
    public String getPassword(){
        return company.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> "ROLE_COMPANY");
        return authorities;
    }

    @Override
    public String getUsername(){
        return company.getEmail();
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }
}
