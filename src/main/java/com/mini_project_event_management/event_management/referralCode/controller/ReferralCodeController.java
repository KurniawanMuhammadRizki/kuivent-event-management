package com.mini_project_event_management.event_management.referralCode.controller;

import com.mini_project_event_management.event_management.referralCode.dto.ReferralCodeDto;
import com.mini_project_event_management.event_management.referralCode.service.ReferralCodeService;
import com.mini_project_event_management.event_management.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/referral-code")
public class ReferralCodeController {
    private final ReferralCodeService referralCodeService;

    public  ReferralCodeController(ReferralCodeService referralCodeService){
        this.referralCodeService = referralCodeService;
    }

    @PostMapping()
    public ResponseEntity<Response<Object>> createReferralCode(@Validated @RequestBody ReferralCodeDto referralCodeDto){
        referralCodeService.addReferralCode(referralCodeDto);
        return  Response.successfulResponse("Referral code created successfully");
    }
}
