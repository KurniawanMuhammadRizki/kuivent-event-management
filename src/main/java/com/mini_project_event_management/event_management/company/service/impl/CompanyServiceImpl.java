package com.mini_project_event_management.event_management.company.service.impl;

import com.mini_project_event_management.event_management.company.dto.CompanyDto;
import com.mini_project_event_management.event_management.company.dto.RegisterCompanyRequestDto;
import com.mini_project_event_management.event_management.company.dto.RegisterCompanyResponseDto;
import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.company.repository.CompanyRepository;
import com.mini_project_event_management.event_management.company.service.CompanyService;
import com.mini_project_event_management.event_management.coupon.service.CouponService;
import com.mini_project_event_management.event_management.exceptions.AlreadyExistException;
import com.mini_project_event_management.event_management.exceptions.DataNotFoundException;
import com.mini_project_event_management.event_management.helpers.CurrentUser;
import com.mini_project_event_management.event_management.helpers.SlugifyHelper;
import com.mini_project_event_management.event_management.point.service.PointService;
import com.mini_project_event_management.event_management.referralCode.dto.ReferralCodeDto;
import com.mini_project_event_management.event_management.referralCode.entity.ReferralCode;
import com.mini_project_event_management.event_management.referralCode.service.ReferralCodeService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.java.Log;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
     private final CompanyRepository companyRepository;
     private final PasswordEncoder passwordEncoder;
     private final ReferralCodeService referralCodeService;
     private final CouponService couponService;
     private final PointService pointService;
     private final CurrentUser currentUser;

     public CompanyServiceImpl(
             CompanyRepository companyRepository,
             PasswordEncoder passwordEncoder,
             @Lazy ReferralCodeService referralCodeService,
             @Lazy CouponService couponService,
             @Lazy PointService pointService,
             @Lazy CurrentUser currentUser
     ) {
          this.companyRepository = companyRepository;
          this.passwordEncoder = passwordEncoder;
          this.referralCodeService = referralCodeService;
          this.couponService = couponService;
          this.pointService = pointService;
          this.currentUser = currentUser;
     }

     Instant now = Instant.now();

     @Cacheable(value = "getCompanyByEmail", key = "#email")
     @Override
     public Company getCompanyByEmail(String email) {
          Optional<Company> company = companyRepository.findByEmail(email);
          if (company.isEmpty()) {
               throw new DataNotFoundException("Company not found");
          }
          return company.orElse(null);
     }


     @Cacheable(value = "getCompanyBySlug", key = "#slug")
     @Override
     public Company getCompanyBySlug(String slug) {
          Optional<Company> company = companyRepository.findBySlug(slug);
          if (company.isEmpty()) {
               throw new DataNotFoundException("Company not found");
          }
          return company.orElse(null);
     }

     @Override
     public CompanyDto getCompanyDtoBySlug(String slug) {
          Company company = getCompanyBySlug(slug);
          CompanyDto companyDto = company.toCompanyDto();
          return companyDto;
     }

     public List<CompanyDto> getAllCompany() {
          List<Company> companies = companyRepository.findAll();
          return companies.stream().map(Company::toCompanyDto).toList();
     }

     @Cacheable(value = "getCompanyById", key = "#id")
     @Override
     public Company getCompanyById(Long id) {
          Optional<Company> company = companyRepository.findById(id);
          if (company.isEmpty()) {
               throw new DataNotFoundException("Company not found");
          }
          return company.orElse(null);
     }

     @Override
     @Transactional
     @RateLimiter(name = "default")
     public RegisterCompanyResponseDto register(RegisterCompanyRequestDto registerDto) {
          Boolean isNameExist = companyRepository.existsByName(registerDto.getName());
          if (isNameExist) {
               throw new AlreadyExistException("Company name already exist");
          }

          Boolean isEmailExist = companyRepository.existsByEmail(registerDto.getEmail());
          if (isEmailExist) {
               throw new AlreadyExistException("Company email already exist");
          }

          Company company = registerDto.toEntity();
          String slug = SlugifyHelper.slugify(registerDto.getName());
          var password = passwordEncoder.encode(company.getPassword());
          company.setPassword(password);
          company.setSlug(slug);

          var companyRegistered = companyRepository.save(company);

          ReferralCodeDto referralCodeDto = new ReferralCodeDto();
          referralCodeDto.setCompanyId(companyRegistered.getId());
          referralCodeDto.setCode(slug);
          referralCodeService.addReferralCode(referralCodeDto);

          if (!registerDto.getReferralCode().isBlank()) {
               ReferralCode referralCode = referralCodeService.getReferralCodeByCode(registerDto.getReferralCode());
               couponService.addCoupon(companyRegistered.getId(), referralCode.getId());
               pointService.addPoint(referralCode.getCompany().getId());
          }

          RegisterCompanyResponseDto registerCompanyResponseDto = new RegisterCompanyResponseDto();
          registerCompanyResponseDto.setId(companyRegistered.getId());
          registerCompanyResponseDto.setAddress(companyRegistered.getAddress());
          registerCompanyResponseDto.setName(companyRegistered.getName());
          registerCompanyResponseDto.setCity(companyRegistered.getCity());
          registerCompanyResponseDto.setEmail(companyRegistered.getEmail());
          registerCompanyResponseDto.setWebsiteUrl(companyRegistered.getWebsiteUrl());
          registerCompanyResponseDto.setPhoneNumber(companyRegistered.getPhoneNumber());

          return registerCompanyResponseDto;
     }

     @Override
     public CompanyDto getCompany() {
          Long companyId = currentUser.getAuthorizedCompanyId();
          Company company = getCompanyById(companyId);
          return company.toCompanyDto();
     }


}
