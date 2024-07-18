package com.mini_project_event_management.event_management.coupon.controller;

import com.mini_project_event_management.event_management.coupon.service.CouponService;
import com.mini_project_event_management.event_management.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CouponController {
     private final CouponService couponService;

     public CouponController(CouponService couponService) {
          this.couponService = couponService;
     }

     @PostMapping("/companyId")
     public ResponseEntity<Response<Integer>> getCountCouponByCompanyId(@PathVariable Integer companyId) {
          Integer result = couponService.getCountCouponByCompanyId(Long.valueOf(companyId));
          return Response.successfulResponse("Point fetched successfully", result);
     }
}
