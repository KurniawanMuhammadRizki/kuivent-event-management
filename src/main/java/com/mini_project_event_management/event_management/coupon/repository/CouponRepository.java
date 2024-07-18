package com.mini_project_event_management.event_management.coupon.repository;

import com.mini_project_event_management.event_management.coupon.entity.Coupon;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface CouponRepository extends JpaRepository<Coupon,  Long> {
     @Query("SELECT COUNT(c) FROM Coupon c WHERE c.companyId = :companyId")
     Integer countByCompanyId(@Param("companyId") Long companyId);
}
