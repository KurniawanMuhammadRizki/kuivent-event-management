package com.mini_project_event_management.event_management.referralCode.repository;

import com.mini_project_event_management.event_management.referralCode.entity.ReferralCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReferralCodeRepository extends JpaRepository<ReferralCode, Long> {
    Optional<ReferralCode> findByCode(String code);
}
