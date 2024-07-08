package com.mini_project_event_management.event_management.auth.service.impl;

import com.mini_project_event_management.event_management.auth.entity.OrganizerAuth;
import com.mini_project_event_management.event_management.auth.repository.AuthRedisRepository;
import com.mini_project_event_management.event_management.auth.service.AuthService;
import com.mini_project_event_management.event_management.company.repository.CompanyRepository;
import com.mini_project_event_management.event_management.organizer.repository.OrganizerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final CompanyRepository companyRepository;
    private final OrganizerRepository organizerRepository;
    private final AuthRedisRepository authRedisRepository;

    public AuthServiceImpl(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, CompanyRepository companyRepository, AuthRedisRepository authRedisRepository, OrganizerRepository organizerRepository){
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.companyRepository = companyRepository;
        this.authRedisRepository = authRedisRepository;
        this.organizerRepository = organizerRepository;
    }
// ini yg bener
    public String generateToken(Authentication authentication){
        Instant now = Instant.now();

        String scope = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        var existingKey = authRedisRepository.getJwtKey(authentication.getName());
        if(existingKey != null){
            return existingKey;
        }
        //old version
        //JwtClaimsSet claims = JwtClaimsSet.builder().issuer("self").issuedAt(now).expiresAt(now.plus(1, ChronoUnit.HOURS    )).subject(authentication.getName()).claim("authorities", scope).build();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .claim("authorities", authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .build();
        var jwt =  jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        authRedisRepository.saveJwtKey(authentication.getName(), jwt);
        return jwt;
    }

}
