package com.mini_project_event_management.event_management.invoice.service.impl;

import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.company.service.CompanyService;
import com.mini_project_event_management.event_management.coupon.entity.Coupon;
import com.mini_project_event_management.event_management.coupon.service.CouponService;
import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.event.service.EventService;
import com.mini_project_event_management.event_management.invoice.dto.InvoiceDto;
import com.mini_project_event_management.event_management.invoice.entity.Invoice;
import com.mini_project_event_management.event_management.invoice.repository.InvoiceRepository;
import com.mini_project_event_management.event_management.invoice.service.InvoiceService;
import com.mini_project_event_management.event_management.voucher.entity.Voucher;
import com.mini_project_event_management.event_management.voucher.service.VoucherService;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {
     private final InvoiceRepository invoiceRepository;
     private final EventService eventService;
     private final CompanyService companyService;
     private final VoucherService voucherService;
     private final CouponService couponService;

     public InvoiceServiceImpl(InvoiceRepository invoiceRepository, EventService eventService, CouponService couponService, VoucherService voucherService, CompanyService companyService) {
          this.invoiceRepository = invoiceRepository;
          this.eventService = eventService;
          this.couponService = couponService;
          this.voucherService = voucherService;
          this.companyService = companyService;
     }

     @Override
     public void generateInvoice(InvoiceDto invoiceDto) {
          double finalPrice = invoiceDto.getPrice();
          Event event = eventService.getEventById(invoiceDto.getEventId());
          Company company = companyService.getCompanyById(invoiceDto.getCompanyId());
          Invoice invoice = invoiceDto.toInvoice();
          invoice.setEvent(event);
          invoice.setCompany(company);

          if (invoiceDto.getCouponId() != null) {
               Coupon coupon = couponService.getCouponById(invoiceDto.getCouponId());
               invoice.setCoupon(coupon);
               finalPrice -= invoiceDto.getPrice() * 0.1;
          }

          if (invoiceDto.getVoucherId() != null) {
               Voucher voucher = voucherService.getVoucherById(invoiceDto.getVoucherId());
               invoice.setVoucher(voucher);
               finalPrice -= invoiceDto.getPrice() * voucher.getDiscountPercent() / 100;
          }

          if (invoiceDto.getPointAmount() != null) {
               finalPrice -= invoiceDto.getPointAmount();
          }

          invoice.setTotalPrice((float) finalPrice);
          invoiceRepository.save(invoice);
     }
}
