package com.mini_project_event_management.event_management.voucher.repository;

import com.mini_project_event_management.event_management.voucher.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    Boolean existByName(String name);
    Boolean existByCode(String code);
}
