package com.mini_project_event_management.event_management.auth.entity;

import com.mini_project_event_management.event_management.users.entity.Users;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class UsersAuth extends Users implements UserDetails {
    private final Users users;
    public UsersAuth(Users users){
        this.users = users;
    }

    @Override
    public  String getPassword(){
        return  users.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> "ROLE_USER");
        return authorities;
    }

    @Override
    public String getUsername(){
        return  users.getEmail();
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
