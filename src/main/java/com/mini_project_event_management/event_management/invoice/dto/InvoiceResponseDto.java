package com.mini_project_event_management.event_management.invoice.dto;

import com.mini_project_event_management.event_management.block.entity.Block;
import com.mini_project_event_management.event_management.invoice.entity.Invoice;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class InvoiceResponseDto {
     private String eventName;
     private String city;
     private String address;
     private LocalDate dateStart;
     private LocalDate dateEnd;
     private LocalTime hourStart;
     private LocalTime hourEnd;
     private String eventType;

     private String email;
     private String companyName;

     private String categoryName;
     private Float price;

     private String blockName;

     private Boolean voucherUsed;
     private String voucherName;
     private Integer discountPercent;

     private Boolean couponUsed;

     private Boolean pointUsed;
     private Integer pointAmount;

     private String invoiceCode;
     private Float totalPrice;

}
