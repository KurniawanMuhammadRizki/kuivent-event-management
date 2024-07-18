package com.mini_project_event_management.event_management.coupon.controller;

import com.mini_project_event_management.event_management.coupon.service.CouponService;
import com.mini_project_event_management.event_management.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController {
     private final CouponService couponService;

     public CouponController(CouponService couponService) {
          this.couponService = couponService;
     }

     @GetMapping("/{referralId}")
     public ResponseEntity<Response<Integer>> getCountCouponByReferralId(@PathVariable Integer referralId) {
          Integer result = couponService.getCountCouponByReferralId(Long.valueOf(referralId));
          return Response.successfulResponse("Total coupon used", result);
     }
}
