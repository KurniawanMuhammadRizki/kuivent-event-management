package com.mini_project_event_management.event_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

     @Bean
     public CorsFilter corsFilter() {
          CorsConfiguration corsConfiguration = new CorsConfiguration();
          corsConfiguration.addAllowedOrigin("http://localhost:3000");
          corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
          corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
          corsConfiguration.setAllowCredentials(true);

          UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
          source.registerCorsConfiguration("/**", corsConfiguration);

          return new CorsFilter(source);
     }
}
