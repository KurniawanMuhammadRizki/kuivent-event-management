package com.mini_project_event_management.event_management.config;

import com.mini_project_event_management.event_management.auth.service.impl.UserDetailsServiceImpl;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Log
public class SecurityConfig {

     private final RsaKeyConfigProperties rsaKeyConfigProperties;

     private final UserDetailsServiceImpl userDetailsService;
     private final CorsConfigurationSourceImpl corsConfigurationSource;

     public SecurityConfig(RsaKeyConfigProperties rsaKeyConfigProperties, UserDetailsServiceImpl userDetailsService, CorsConfigurationSourceImpl corsConfigurationSource) {
          this.rsaKeyConfigProperties = rsaKeyConfigProperties;
          this.userDetailsService = userDetailsService;
          this.corsConfigurationSource = corsConfigurationSource;
     }

     @Bean
     public AuthenticationManager authManager() {
          var authProvider = new DaoAuthenticationProvider();
          authProvider.setUserDetailsService(userDetailsService);
          authProvider.setPasswordEncoder(passwordEncoder());
          return new ProviderManager(authProvider);
     }

     @Bean
     PasswordEncoder passwordEncoder() {
          return new BCryptPasswordEncoder();
     }

     @Bean
     public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
          return http.csrf(AbstractHttpConfigurer::disable).cors(cors -> cors.configurationSource(corsConfigurationSource)).authorizeHttpRequests(auth -> {
                       auth.requestMatchers("error/**").permitAll();
                       auth.requestMatchers("api/v1/auth/**").permitAll();
                       auth.requestMatchers("api/v1/user/register").permitAll();
                       auth.requestMatchers("api/v1/company/register").permitAll();
                       auth.requestMatchers(HttpMethod.GET, "api/v1/company/{slug}").permitAll();
                       auth.requestMatchers(HttpMethod.GET, "api/v1/rating/{eventId}").permitAll();
                       auth.requestMatchers(HttpMethod.GET, "api/v1/products/{slug}").permitAll();
                       auth.requestMatchers("api/v1/users/register").permitAll();
                       auth.requestMatchers("api/v1/organizer/register").permitAll();
                       auth.requestMatchers("api/v1/event/**").permitAll();
                       auth.requestMatchers("api/v1/company/**").permitAll();
                       auth.requestMatchers("api/v1/block/**").permitAll();
                       auth.requestMatchers("api/v1/event-topic/**").permitAll();
                       auth.requestMatchers("api/v1/rating/").permitAll();
                       auth.requestMatchers("api/v1/speakers/{slug}").permitAll();
                       auth.requestMatchers("api/v1/user/forget-password").permitAll();
                       auth.requestMatchers(HttpMethod.POST, "api/v1/voucher").hasAuthority("SCOPE_ROLE_ORGANIZER");
                       auth.requestMatchers("api/v1/voucher/organizer").hasAuthority("SCOPE_ROLE_ORGANIZER");
                       auth.requestMatchers(HttpMethod.POST,"api/v1/speakers").hasAuthority("SCOPE_ROLE_ORGANIZER");
                       auth.requestMatchers("api/v1/invoice/income").hasAuthority("SCOPE_ROLE_ORGANIZER");
                       auth.requestMatchers(HttpMethod.POST,"api/v1/invoice").hasAuthority("SCOPE_ROLE_COMPANY");
                       auth.anyRequest().authenticated();
                  }).sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                  .oauth2ResourceServer((oauth2) -> oauth2.jwt((jwt) -> jwt.decoder(jwtDecoder()))).userDetailsService(userDetailsService).httpBasic(Customizer.withDefaults()).build();
     }


     @Bean
     public JwtDecoder jwtDecoder() {
          return NimbusJwtDecoder.withPublicKey(rsaKeyConfigProperties.publicKey()).build();
     }

     @Bean
     JwtEncoder jwtEncoder() {
          JWK jwk = new RSAKey.Builder(rsaKeyConfigProperties.publicKey()).privateKey(rsaKeyConfigProperties.privateKey()).build();

          JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
          return new NimbusJwtEncoder(jwks);
     }


}
