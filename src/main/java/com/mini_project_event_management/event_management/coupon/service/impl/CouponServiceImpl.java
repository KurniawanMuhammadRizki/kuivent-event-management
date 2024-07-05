package com.mini_project_event_management.event_management.coupon.service.impl;

import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.company.service.CompanyService;
import com.mini_project_event_management.event_management.coupon.dto.AddCouponDto;
import com.mini_project_event_management.event_management.coupon.entity.Coupon;
import com.mini_project_event_management.event_management.coupon.repository.CouponRepository;
import com.mini_project_event_management.event_management.coupon.service.CouponService;
import com.mini_project_event_management.event_management.referralCode.entity.ReferralCode;
import com.mini_project_event_management.event_management.referralCode.service.ReferralCodeService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Service
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final CompanyService companyService;
    private final ReferralCodeService referralCodeService;

    public  CouponServiceImpl(CouponRepository couponRepository, CompanyService companyService, ReferralCodeService referralCodeService){
        this.couponRepository = couponRepository;
        this.companyService = companyService;
        this.referralCodeService = referralCodeService;
    }

    Instant now = Instant.now();


    @Override
    public void addCoupon(Long companyId, Long referralId){
        Company company = companyService.getCompanyById(companyId);
        ReferralCode referralCode = referralCodeService.getReferralCodeById(referralId);
        //Instant expiredAt = now.plus(3, ChronoUnit.MONTHS);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(now, ZoneId.systemDefault());

        // Add 3 months to LocalDateTime
        LocalDateTime futureDateTime = localDateTime.plus(3, ChronoUnit.MONTHS);

        // Convert back to Instant
        Instant expiredAt = futureDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Coupon coupon = new Coupon();
        coupon.setCompany(company);
        coupon.setReferralCode(referralCode);
        coupon.setCreatedAt(now);
        coupon.setUpdatedAt(now);
        coupon.setValid(true);
        coupon.setExpiredAt(expiredAt);
        couponRepository.save(coupon);

    }




}
