package com.mini_project_event_management.event_management.auth.entity;

import com.mini_project_event_management.event_management.organizer.entity.Organizer;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class OrganizerAuth extends Organizer implements UserDetails {

    private final Organizer organizer;
    public OrganizerAuth(Organizer organizer){
        this.organizer = organizer;
    }

    @Override
    public String getPassword(){
        return organizer.getPassword();
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities(){
//        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(() -> "ROLE_ORGANIZER" );
//        return authorities;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ORGANIZER"));
        return authorities;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<Roles> roles = this.user.getRoles();
//        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
//                .collect(Collectors.toCollection(ArrayList::new));
//    }


    @Override
    public String getUsername(){
        return organizer.getEmail();
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
