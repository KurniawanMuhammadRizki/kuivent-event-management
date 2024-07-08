package com.mini_project_event_management.event_management.voucher.controller;

import com.mini_project_event_management.event_management.responses.Response;
import com.mini_project_event_management.event_management.voucher.dto.VoucherDto;
import com.mini_project_event_management.event_management.voucher.service.VoucherService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/voucher")
public class VoucherController {
    private final VoucherService voucherService;

    public VoucherController(VoucherService voucherService){
        this.voucherService = voucherService;
    }

    @PostMapping
    @RolesAllowed("ORGANIZER")
    public ResponseEntity<Response<Object>> addVoucher(@Validated @RequestBody VoucherDto voucherDto){
        voucherService.addVoucher(voucherDto);
        return Response.successfulResponse("Voucher added successfully");
    }

}
