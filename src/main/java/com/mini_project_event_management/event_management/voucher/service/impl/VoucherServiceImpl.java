package com.mini_project_event_management.event_management.voucher.service.impl;

import com.mini_project_event_management.event_management.exceptions.AlreadyExistException;
import com.mini_project_event_management.event_management.exceptions.DataNotFoundException;
import com.mini_project_event_management.event_management.helpers.CurrentUser;
import com.mini_project_event_management.event_management.organizer.entity.Organizer;
import com.mini_project_event_management.event_management.organizer.service.OrganizerService;
import com.mini_project_event_management.event_management.voucher.dto.VoucherDto;
import com.mini_project_event_management.event_management.voucher.entity.Voucher;
import com.mini_project_event_management.event_management.voucher.repository.VoucherRepository;
import com.mini_project_event_management.event_management.voucher.service.VoucherService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoucherServiceImpl implements VoucherService {
    private final VoucherRepository voucherRepository;
    private final OrganizerService organizerService;
    private final CurrentUser currentUser;
     public VoucherServiceImpl(VoucherRepository voucherRepository, OrganizerService organizerService, CurrentUser currentUser){
         this.voucherRepository = voucherRepository;
         this.organizerService = organizerService;
         this.currentUser = currentUser;
     }

     @Override
    public void addVoucher(VoucherDto voucherDto){
         Long organizerId = currentUser.getAuthorizedOrganizerId();
         Organizer organizer = organizerService.getOrganizerById(organizerId);
         Voucher voucher = new Voucher();

         Boolean isNameExist = voucherRepository.existsByName(voucherDto.getName());
         if (isNameExist){
             throw new AlreadyExistException("Name already exist");
         }

         Boolean isCodeExist = voucherRepository.existsByCode(voucherDto.getCode());
         if(isCodeExist){
             throw new AlreadyExistException("Code already exist");
         }

         voucher.setCode(voucherDto.getCode());
         voucher.setName(voucherDto.getName());
         voucher.setOrganizer(organizer);
         voucher.setDiscountPercent(voucherDto.getDiscountPercent());

         voucherRepository.save(voucher);
     }

     @Override
     @Cacheable(value = "getVoucherById", key = "#id")
     public Voucher getVoucherById(Long id){
          Optional<Voucher> voucher = voucherRepository.findById(id);
          if(voucher.isEmpty() || voucher == null){
               throw new DataNotFoundException("Voucher not found");
          }
          return voucher.orElse(null);
     }

     @Override
     @Cacheable(value = "getVoucherByCode", key = "#code")
     public Voucher getVoucherByCode(String code){
          Optional<Voucher> voucher = Optional.ofNullable(voucherRepository.findByCode(code));
          if(voucher.isEmpty() || voucher == null){
               throw new DataNotFoundException("Voucher not found");
          }
          return voucher.orElse(null);
     }

     @Override
     public List<VoucherDto> getVoucherListByOrganizerId(){
          Long organizerId =  currentUser.getAuthorizedOrganizerId();
          List<Voucher> vouchers = voucherRepository.findByOrganizerId(organizerId);
          if(vouchers == null || vouchers.isEmpty()){
               throw new DataNotFoundException("Voucher not found");
          }
          return vouchers.stream().map(Voucher::toVoucherDto).collect(Collectors.toList());
     }



}
