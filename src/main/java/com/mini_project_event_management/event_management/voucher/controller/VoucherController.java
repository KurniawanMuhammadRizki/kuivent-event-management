package com.mini_project_event_management.event_management.voucher.controller;

import com.mini_project_event_management.event_management.responses.Response;
import com.mini_project_event_management.event_management.voucher.dto.VoucherDto;
import com.mini_project_event_management.event_management.voucher.service.VoucherService;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
@RequestMapping("/api/v1/voucher")
public class VoucherController {
    private final VoucherService voucherService;

    public VoucherController(VoucherService voucherService){
        this.voucherService = voucherService;
    }

    @PostMapping
    public ResponseEntity<Response<Object>> addVoucher(@Validated @RequestBody VoucherDto voucherDto){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        log.info(authentication.toString());
        voucherService.addVoucher(voucherDto);
        return Response.successfulResponse("Voucher added successfully");
    }

    @GetMapping("/organizer")
    public ResponseEntity<Response<List<VoucherDto>>> getVoucherByOrganizerId(){
        List<VoucherDto> voucherDtos = voucherService.getVoucherListByOrganizerId();
        return Response.successfulResponse("Voucher fetched successfully", voucherDtos);
    }

}
