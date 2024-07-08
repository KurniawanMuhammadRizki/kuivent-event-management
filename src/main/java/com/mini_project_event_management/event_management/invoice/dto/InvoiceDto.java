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

     private Long voucherId;
     private Long couponId;
//     private LocalDate dateStart;
//     private LocalDate dateEnd;
//     private LocalTime hourStart;
//     private LocalTime hourEnd;
//
//     @NotBlank(message = "Category name cannot be empty")
//     private String categoryName;
//
//     @NotNull(message = "Price cannot be null")
//     @Min(value = 0, message = "Price must be zero or positive")
//     private Float price;
//
//     @NotBlank(message = "Event name cannot be empty")
//     private String eventName;
//
//     @NotBlank(message = "City cannot be empty")
//     private String city;
//
//     @NotBlank(message = "Event type cannot be empty")
//     private String eventType;
//
//     @NotBlank(message = "Email cannot be empty")
//     private String email;
//
//     private String voucherName;
//
//     @Min(value = 0, message = "Discount percent must be zero or positive")
//     private Integer discountPercent;
//
//     @NotNull(message = "Coupon used cannot be null")
//     private Boolean couponUsed;

     @Min(value = 0, message = "Point amount must be zero or positive")
     private Integer pointAmount;
     //private Float totalPrice;

//     public Invoice toInvoice() {
//          Invoice invoice = new Invoice();
//          //invoice.setEmail(email);
////          invoice.setHourEnd(hourEnd);
////          invoice.setHourStart(hourStart);
////          invoice.setDateStart(dateStart);
////          invoice.setDateEnd(dateEnd);
//         // invoice.setCategoryName(categoryName);
//        //  invoice.setPrice(price);
//         // invoice.setEventName(eventName);
//        //  invoice.setCity(city);
//        //  invoice.setEventType(eventType);
//         // invoice.setVoucherName(voucherName);
//        //  invoice.setDiscountPercent(discountPercent);
//         // invoice.setCouponUsed(couponUsed);
//          //invoice.setPointAmount(pointAmount);
//          return invoice;
//     }

}
