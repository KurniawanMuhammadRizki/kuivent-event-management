package com.mini_project_event_management.event_management.organizer.dto;

import com.mini_project_event_management.event_management.voucher.dto.VoucherDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class OrganizerDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String websiteUrl;
    private String profileUrl;
    private String about;
    private List<VoucherDto> vouchers;
}
