package com.mini_project_event_management.event_management.config.authorities;

import org.springframework.security.core.GrantedAuthority;

public class SimpleGrantedAuthority implements GrantedAuthority {
     private final String role;

     public SimpleGrantedAuthority(String role) {
          this.role = role;
     }

     @Override
     public String getAuthority() {
          return this.role;
     }
}
