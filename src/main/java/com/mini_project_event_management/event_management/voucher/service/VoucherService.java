package com.mini_project_event_management.event_management.voucher.service;

import com.mini_project_event_management.event_management.voucher.dto.VoucherDto;
import com.mini_project_event_management.event_management.voucher.entity.Voucher;

import java.util.List;

public interface VoucherService {
    void addVoucher(VoucherDto voucherDto);
    Voucher getVoucherById(Long id);
    Voucher getVoucherByCode(String code);
    List<VoucherDto> getVoucherListByOrganizerId();
}
