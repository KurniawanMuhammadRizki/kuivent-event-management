package com.mini_project_event_management.event_management.invoice.entity;

import com.mini_project_event_management.event_management.block.entity.Block;
import com.mini_project_event_management.event_management.category.entity.Category;
import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.coupon.entity.Coupon;
import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.invoice.dto.InvoiceResponseDto;
import com.mini_project_event_management.event_management.voucher.entity.Voucher;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "invoice")
public class Invoice {
     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_id_gen")
     @SequenceGenerator(name = "invoice_id_gen", sequenceName = "invoice_id_seq", allocationSize = 1)
     @Column(name = "id", nullable = false)
     private Long id;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "event_id", nullable = false)
     private Event event;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "company_id", nullable = false)
     private Company company;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "category_id", nullable = false)
     private Category category;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "voucher_id")
     private Voucher voucher;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "coupon_id")
     private Coupon coupon;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "block_id")
     private Block block;

     @Column(name = "date_start", nullable = false)
     private LocalDate dateStart;

     @Column(name = "date_end")
     private LocalDate dateEnd;

     @Column(name = "hour_start", nullable = false)
     private LocalTime hourStart;

     @Column(name = "hour_end")
     private LocalTime hourEnd;

     @Column(name = "category_name", nullable = false)
     private String categoryName;

     @Column(name = "price", nullable = false)
     private Float price;

     @Column(name = "email", nullable = false)
     private String email;

     @Column(name = "event_name", nullable = false)
     private String eventName;

     @Column(name = "city", nullable = false)
     private String city;

     @Column(name = "block_name", nullable = false)
     private String BlockName;

     @Column(name = "event_type", nullable = false)
     private String eventType;

     @Column(name = "invoice_code", nullable = false)
     private String invoiceCode;

     @Column(name = "voucher_name")
     private String voucherName;

     @Column(name = "discount_percent")
     private Integer discountPercent;

     @Column(name = "coupon_used", nullable = false)
     private Boolean couponUsed;

     @Column(name = "point_amount")
     private Integer pointAmount;

     @Column(name = "total_price", nullable = false)
     private Float totalPrice;

     @ColumnDefault("CURRENT_TIMESTAMP")
     @Column(name = "created_at")
     private Instant createdAt = Instant.now();

     @ColumnDefault("CURRENT_TIMESTAMP")
     @Column(name = "updated_at")
     private Instant updatedAt = Instant.now();

     @Column(name = "deleted_at")
     private Instant deletedAt;

     @PrePersist
     void onSave() {
          this.createdAt = Instant.now();
          this.updatedAt = Instant.now();
     }

     @PreUpdate
     void onUpdate() {
          this.updatedAt = Instant.now();
     }

     @PreDestroy
     void onDelete() {
          this.deletedAt = Instant.now();
     }

     public static InvoiceResponseDto toInvoiceResponseDto(Invoice invoice) {
          InvoiceResponseDto dto = new InvoiceResponseDto();
          dto.setEventName(invoice.getEventName());
          dto.setCity(invoice.getCity());
          dto.setAddress(invoice.getEvent().getAddress());
          dto.setDateStart(invoice.getDateStart());
          dto.setDateEnd(invoice.getDateEnd());
          dto.setHourStart(invoice.getHourStart());
          dto.setHourEnd(invoice.getHourEnd());
          dto.setEventType(invoice.getEventType());
          dto.setEmail(invoice.getEmail());
          dto.setCompanyName(invoice.getCompany().getName());
          dto.setCategoryName(invoice.getCategoryName());
          dto.setPrice(invoice.getPrice());
          dto.setBlockName(invoice.getBlockName());
          if (invoice.getVoucher() == null) {
               dto.setVoucherUsed(false);
               dto.setDiscountPercent(null);
          } else {
               dto.setVoucherUsed(true);
               dto.setDiscountPercent(invoice.getVoucher().getDiscountPercent());
          }

          dto.setVoucherName(invoice.getVoucherName());
          dto.setCouponUsed(invoice.getCouponUsed());
          if (invoice.getPointAmount() == null) {
               dto.setPointUsed(false);
          } else {
               dto.setPointUsed(true);
          }

          dto.setPointUsed(invoice.getCouponUsed());
          dto.setInvoiceCode(invoice.getInvoiceCode());
          dto.setTotalPrice(invoice.getTotalPrice());
          return dto;
     }
}
