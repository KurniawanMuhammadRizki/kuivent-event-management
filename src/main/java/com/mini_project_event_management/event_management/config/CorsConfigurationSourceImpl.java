package com.mini_project_event_management.event_management.config;

import io.github.resilience4j.core.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfigurationSourceImpl implements CorsConfigurationSource {
     @Override
     public CorsConfiguration getCorsConfiguration(@NonNull HttpServletRequest request) {
          CorsConfiguration corsConfiguration = new CorsConfiguration();
          corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
          corsConfiguration.setAllowedOriginPatterns(List.of("http://localhost:3000"));
          corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT", "OPTIONS", "PATCH", "DELETE"));
          corsConfiguration.setAllowCredentials(true);
          corsConfiguration.setExposedHeaders(List.of("Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
          return corsConfiguration;
     }
}