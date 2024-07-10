package com.mini_project_event_management.event_management.invoice.service.impl;

import com.mini_project_event_management.event_management.category.dto.CategoryDto;
import com.mini_project_event_management.event_management.category.entity.Category;
import com.mini_project_event_management.event_management.category.service.CategoryService;
import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.company.service.CompanyService;
import com.mini_project_event_management.event_management.coupon.entity.Coupon;
import com.mini_project_event_management.event_management.coupon.service.CouponService;
import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.event.service.EventService;
import com.mini_project_event_management.event_management.exceptions.ApplicationException;
import com.mini_project_event_management.event_management.exceptions.DataNotFoundException;
import com.mini_project_event_management.event_management.invoice.dto.InvoiceDto;
import com.mini_project_event_management.event_management.invoice.entity.Invoice;
import com.mini_project_event_management.event_management.invoice.repository.InvoiceRepository;
import com.mini_project_event_management.event_management.invoice.service.InvoiceService;
import com.mini_project_event_management.event_management.voucher.entity.Voucher;
import com.mini_project_event_management.event_management.voucher.service.VoucherService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.ZoneId;
@Log
@Service
public class InvoiceServiceImpl implements InvoiceService {
     private final InvoiceRepository invoiceRepository;
     private final EventService eventService;
     private final CompanyService companyService;
     private final VoucherService voucherService;
     private final CouponService couponService;
     private final CategoryService categoryService;

     public InvoiceServiceImpl(InvoiceRepository invoiceRepository, EventService eventService, CouponService couponService, VoucherService voucherService, CompanyService companyService, CategoryService categoryService) {
          this.invoiceRepository = invoiceRepository;
          this.eventService = eventService;
          this.couponService = couponService;
          this.voucherService = voucherService;
          this.companyService = companyService;
          this.categoryService = categoryService;
     }

     @Override
     @Transactional
     public void generateInvoice(InvoiceDto invoiceDto) {

          Event event = eventService.getEventById(invoiceDto.getEventId());
          Company company = companyService.getCompanyById(invoiceDto.getCompanyId());
          CategoryDto categoryDto = categoryService.getCategoryById(invoiceDto.getCategoryId());
          Invoice invoice = new Invoice();
          Category category = categoryDto.toEntity();
          double finalPrice = categoryDto.getPrice();

          category.setEvent(event);
          invoice.setCategory(category);
          invoice.setCategoryName(category.getName());
          invoice.setPrice((float) category.getPrice());
          invoice.setEvent(event);
          invoice.setEventName(event.getName());
          invoice.setHourStart(event.getHourStart().toInstant().atZone(ZoneId.systemDefault()).toLocalTime());
          invoice.setHourEnd(event.getHourEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalTime());
          invoice.setDateStart(event.getDateStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
          invoice.setDateEnd(event.getDateEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
          invoice.setCity(event.getCity());
          //masih ada error
          invoice.setEventType("aw");
          invoice.setCompany(company);
          invoice.setEmail(company.getEmail());

          if (invoiceDto.getVoucherId() != null) {
               //ini harusnya pake pengecekan by Code bukan id
               Voucher voucher = voucherService.getVoucherById(invoiceDto.getVoucherId());
               invoice.setVoucher(voucher);
               invoice.setVoucherName(voucher.getName());
               invoice.setDiscountPercent(voucher.getDiscountPercent());
               finalPrice -= (finalPrice * voucher.getDiscountPercent() / 100);
          }

          if (invoiceDto.getPointAmount() != null) {
               //cek pointnya expired apa engga
               //ini belom ada pengurangan ke companynya
               invoice.setPointAmount(invoiceDto.getPointAmount());
               finalPrice -= invoiceDto.getPointAmount();
          }

          if (invoiceDto.getCouponId() != null) {
               //belom ada set kuponnya user jadi false sama belom ada pengecekan
               Coupon coupon = couponService.getCouponById(invoiceDto.getCouponId());

               if(!coupon.isValid()){
                    throw new DataNotFoundException("You doesn't have valid coupon");
               }

               if(coupon.getExpiredAt().isAfter(Instant.now())){
                    throw new DataNotFoundException("You doesn't have valid coupon");
               }

               invoice.setCoupon(coupon);
               invoice.setCouponUsed(true);

               finalPrice -= (finalPrice * 0.1);
          }

          invoice.setTotalPrice((float) finalPrice);
          //masih ada error mau pake slug aja
          invoice.setInvoiceCode(generateInvoiceCode(event.getName(), company.getEmail()));
          invoiceRepository.save(invoice);
     }

     private String generateInvoiceCode(String eventName, String companyName) {
          SecureRandom random = new SecureRandom();
          String letter = companyName.length() < 3 ? companyName : companyName.substring(0, 3);
          int number = 100 + random.nextInt(900);
          return "INVOICE/"+ eventName.toUpperCase() + "/" + letter.toUpperCase() + number;
     }
}
