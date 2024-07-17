package com.mini_project_event_management.event_management.voucher.repository;

import com.mini_project_event_management.event_management.voucher.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    Boolean existsByName(String name);
    Boolean existsByCode(String code);
    Voucher findByCode(String code);
    List<Voucher> findByOrganizerId(Long id);
}
