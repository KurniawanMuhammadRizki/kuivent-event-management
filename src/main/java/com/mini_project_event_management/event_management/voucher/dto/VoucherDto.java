package com.mini_project_event_management.event_management.voucher.dto;

import com.mini_project_event_management.event_management.voucher.entity.Voucher;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VoucherDto {
    @NotBlank(message = "Name is Required")
    private String name;

    @NotBlank(message = "Code is Required")
    private String code;

    @NotNull(message = "Discount percent cannot be null")
    @Min(value = 0, message = "Discount percent must be zero or positive")
    private int discountPercent;

    private Long organizerId;

    public VoucherDto toVoucherDto(Voucher voucher){
        VoucherDto voucherDto = new VoucherDto();
        voucherDto.setName(voucher.getName());
        voucherDto.setCode(voucher.getCode());
        voucherDto.setDiscountPercent(voucher.getDiscountPercent());
        return voucherDto;
    }
}
