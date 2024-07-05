package com.mini_project_event_management.event_management.referralCode.service.impl;

import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.company.service.CompanyService;
import com.mini_project_event_management.event_management.exceptions.DataNotFoundException;
import com.mini_project_event_management.event_management.referralCode.dto.ReferralCodeDto;
import com.mini_project_event_management.event_management.referralCode.entity.ReferralCode;
import com.mini_project_event_management.event_management.referralCode.repository.ReferralCodeRepository;
import com.mini_project_event_management.event_management.referralCode.service.ReferralCodeService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
public class ReferralCodeServiceImpl implements ReferralCodeService {
    private final ReferralCodeRepository referralCodeRepository;
    private final CompanyService companyService;

    public ReferralCodeServiceImpl(ReferralCodeRepository referralCodeRepository, CompanyService companyService){
        this.referralCodeRepository = referralCodeRepository;
        this.companyService = companyService;
    }

    Instant now = Instant.now();
    @Override
    @Transactional
     public void addReferralCode(ReferralCodeDto referralCodeDto){
        Company company = companyService.getCompanyById(referralCodeDto.getCompanyId());

        ReferralCode referralCode = new ReferralCode();
        referralCode.setCode(referralCodeDto.getCode());
        referralCode.setCompany(company);
        referralCode.setCreatedAt(now);
        referralCode.setUpdatedAt(now);

        referralCodeRepository.save(referralCode);
    }

    @Override
    @Cacheable(value = "checkReferralCode", key = "#code")
    public Boolean checkReferralCode(String code){
        Optional<ReferralCode> referralCode = referralCodeRepository.findByCode(code);
        return referralCode.isPresent();
    }

    @Override
    @Cacheable(value = "getReferralCodeById", key = "#id")
    public ReferralCode getReferralCodeById(Long id){
        Optional<ReferralCode> referralCode = referralCodeRepository.findById(id);
                if(referralCode.isEmpty()){
                    throw  new DataNotFoundException("Referral Code not found");
                }
                return referralCode.orElse(null);
    }

    @Override
    @Cacheable(value = "getReferralCodeByCode", key = "#code")
    public ReferralCode getReferralCodeByCode(String code){
        Optional<ReferralCode> referralCode = referralCodeRepository.findByCode(code);
        if(referralCode.isEmpty()){
            throw  new DataNotFoundException("Referral Code not found");
        }
        return referralCode.orElse(null);
    }
}
