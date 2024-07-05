package com.mini_project_event_management.event_management.referralCode.service;

import com.mini_project_event_management.event_management.referralCode.dto.ReferralCodeDto;
import com.mini_project_event_management.event_management.referralCode.entity.ReferralCode;
import lombok.Data;


public interface ReferralCodeService {
    void addReferralCode(ReferralCodeDto referralCodeDto);
    Boolean checkReferralCode(String code);
    ReferralCode getReferralCodeById(Long id);
    ReferralCode getReferralCodeByCode(String  code);
}
