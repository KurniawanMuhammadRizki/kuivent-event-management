package com.mini_project_event_management.event_management.coupon.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddCouponDto {
    @NotNull(message = "Company id  is required")
    private Long companyId;
    @NotNull(message = "Referral id  is required")
    private Long referralId;
}
