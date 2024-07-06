package com.mini_project_event_management.event_management.voucher.dto;

import com.mini_project_event_management.event_management.voucher.entity.Voucher;
import lombok.Data;

@Data
public class VoucherDto {

    private String name;
    private String code;
    private int discountPercent;
    private Long organizerId;

    public VoucherDto toVoucherDto(Voucher voucher){
        VoucherDto voucherDto = new VoucherDto();
        voucherDto.setName(voucher.getName());
        voucherDto.setCode(voucher.getCode());
        voucherDto.setDiscountPercent(voucher.getDiscountPercent());
        voucherDto.setOrganizerId(voucher.getId());
        return voucherDto;
    }
}
