package com.mini_project_event_management.event_management.referralCode.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReferralCodeDto {
    @NotNull(message = "Company id  is required")
    private Long companyId;
    @NotBlank(message = "Code id  is required")
    private String code;
}
