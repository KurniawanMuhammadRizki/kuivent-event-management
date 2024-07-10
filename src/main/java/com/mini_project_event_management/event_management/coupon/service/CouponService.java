package com.mini_project_event_management.event_management.coupon.service;

import com.mini_project_event_management.event_management.coupon.dto.AddCouponDto;
import com.mini_project_event_management.event_management.coupon.entity.Coupon;

public interface CouponService {
     void addCoupon(Long companyId, Long referralId);
     void setCouponUsed(Long id);
     Coupon getCouponById(Long id);
}
