package com.mini_project_event_management.event_management.invoice.service.impl;

import com.mini_project_event_management.event_management.block.entity.Block;
import com.mini_project_event_management.event_management.block.service.BlockService;
import com.mini_project_event_management.event_management.category.dto.CategoryDto;
import com.mini_project_event_management.event_management.category.entity.Category;
import com.mini_project_event_management.event_management.category.service.CategoryService;
import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.company.service.CompanyService;
import com.mini_project_event_management.event_management.coupon.entity.Coupon;
import com.mini_project_event_management.event_management.coupon.service.CouponService;
import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.event.service.EventService;
import com.mini_project_event_management.event_management.eventType.entity.EventType;
import com.mini_project_event_management.event_management.eventType.service.EventTypeService;
import com.mini_project_event_management.event_management.exceptions.ApplicationException;
import com.mini_project_event_management.event_management.exceptions.DataNotFoundException;
import com.mini_project_event_management.event_management.invoice.dto.InvoiceDto;
import com.mini_project_event_management.event_management.invoice.dto.InvoiceResponseDto;
import com.mini_project_event_management.event_management.invoice.entity.Invoice;
import com.mini_project_event_management.event_management.invoice.repository.InvoiceRepository;
import com.mini_project_event_management.event_management.invoice.service.InvoiceService;
import com.mini_project_event_management.event_management.point.service.PointService;
import com.mini_project_event_management.event_management.voucher.entity.Voucher;
import com.mini_project_event_management.event_management.voucher.service.VoucherService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Log
@Service
public class InvoiceServiceImpl implements InvoiceService {
     private final InvoiceRepository invoiceRepository;
     private final EventService eventService;
     private final CompanyService companyService;
     private final VoucherService voucherService;
     private final CouponService couponService;
     private final CategoryService categoryService;
     private final BlockService blockService;
     private final PointService pointService;
     private final EventTypeService eventTypeService;

     public InvoiceServiceImpl(InvoiceRepository invoiceRepository, EventService eventService, CouponService couponService, VoucherService voucherService, CompanyService companyService, CategoryService categoryService, BlockService blockService, PointService pointService, EventTypeService eventTypeService ) {
          this.invoiceRepository = invoiceRepository;
          this.eventService = eventService;
          this.couponService = couponService;
          this.voucherService = voucherService;
          this.companyService = companyService;
          this.categoryService = categoryService;
          this.blockService = blockService;
          this.pointService = pointService;
          this.eventTypeService = eventTypeService;
     }

     @Override
     @Transactional
     public void generateInvoice(InvoiceDto invoiceDto) {

          Event event = eventService.getEventById(invoiceDto.getEventId());
          Company company = companyService.getCompanyById(invoiceDto.getCompanyId());
          CategoryDto categoryDto = categoryService.getCategoryById(invoiceDto.getCategoryId());
          Block block = blockService.getBlockById(invoiceDto.getBlockId());
          EventType eventType = eventTypeService.getEventTypeById(Math.toIntExact(event.getEventType().getId()));
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
          invoice.setBlock(block);
          invoice.setBlockName(block.getName());
          invoice.setEventType(eventType.getName());
          invoice.setCompany(company);
          invoice.setEmail(company.getEmail());

          if (invoiceDto.getVoucherCode() != null) {
               Voucher voucher = voucherService.getVoucherByCode(invoiceDto.getVoucherCode());
               if(voucher.getDeletedAt() != null){
                    throw new DataNotFoundException("Voucher invalid / deleted");
               }
               invoice.setVoucher(voucher);
               invoice.setVoucherName(voucher.getName());
               invoice.setDiscountPercent(voucher.getDiscountPercent());
               finalPrice -= (finalPrice * voucher.getDiscountPercent() / 100);
          }

          if(invoiceDto.isUsePoint()){
               int companyPoint = pointService.getPointsByCompanyId(company.getId());
               invoice.setPointAmount(companyPoint);
               finalPrice -= companyPoint;
               pointService.softDeletePointsByCompanyId(company.getId());
          }

//          if (invoiceDto.getPointAmount() != null) {
//               //cek pointnya expired apa engga
//               //ini belom ada pengurangan ke companynya
//
//               invoice.setPointAmount(invoiceDto.getPointAmount());
//               finalPrice -= invoiceDto.getPointAmount();
//          }

          if (invoiceDto.getCouponId() != null) {
               Coupon coupon = couponService.getCouponById(invoiceDto.getCouponId());

               if(!coupon.isValid()){
                    throw new DataNotFoundException("You doesn't have valid coupon");
               }

               if(coupon.getExpiredAt().isAfter(Instant.now())){
                    throw new DataNotFoundException("You doesn't have valid coupon");
               }

               invoice.setCoupon(coupon);
               invoice.setCouponUsed(true);
               couponService.setCouponUsed(invoiceDto.getCouponId());
               finalPrice -= (finalPrice * 0.1);
          }

          invoice.setTotalPrice((float) finalPrice);
          invoice.setInvoiceCode(generateInvoiceCode(event.getSlug(), company.getSlug()));
          invoiceRepository.save(invoice);
     }

     private String generateInvoiceCode(String eventName, String companyName) {
          SecureRandom random = new SecureRandom();
          String letter = companyName.length() < 3 ? companyName : companyName.substring(0, 3);
          int number = 100 + random.nextInt(900);
          return "INVOICE/"+ eventName.toUpperCase() + "/" + letter.toUpperCase() + number;
     }

     @Override
     public List<InvoiceResponseDto> getInvoiceByEventId(Long id){
          List<Invoice> invoices = invoiceRepository.findAllByEventId(id);
          if(invoices == null || invoices.isEmpty()){
               throw new DataNotFoundException("Invoice not found");
          }
          return invoices.stream().map(Invoice::toInvoiceResponseDto).collect(Collectors.toList());
     }
}
