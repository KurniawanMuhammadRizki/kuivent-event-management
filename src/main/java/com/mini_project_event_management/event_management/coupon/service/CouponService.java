package com.mini_project_event_management.event_management.coupon.service;

import com.mini_project_event_management.event_management.coupon.dto.AddCouponDto;

public interface CouponService {
    void addCoupon(Long companyId, Long referralId);
}
