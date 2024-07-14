package com.mini_project_event_management.event_management.invoice.dto;

import com.mini_project_event_management.event_management.invoice.entity.Invoice;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class InvoiceDto {
     @NotNull(message = "Event  cannot be null")
     private Long eventId;
     @NotNull(message = "Company  cannot be null")
     private Long companyId;
     @NotNull(message = "Category cannot be null")
     private Long categoryId;
     private String voucherCode;
     private Long couponId;
     private Long blockId;
     @Min(value = 0, message = "Point amount must be zero or positive")
     private Integer pointAmount;
     private  boolean usePoint;
}
