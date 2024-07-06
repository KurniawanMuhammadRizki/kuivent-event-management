package com.mini_project_event_management.event_management.voucher.service.impl;

import com.mini_project_event_management.event_management.exceptions.AlreadyExistException;
import com.mini_project_event_management.event_management.organizer.entity.Organizer;
import com.mini_project_event_management.event_management.organizer.service.OrganizerService;
import com.mini_project_event_management.event_management.voucher.dto.VoucherDto;
import com.mini_project_event_management.event_management.voucher.entity.Voucher;
import com.mini_project_event_management.event_management.voucher.repository.VoucherRepository;
import com.mini_project_event_management.event_management.voucher.service.VoucherService;
import org.springframework.stereotype.Service;

@Service
public class VoucherServiceImpl implements VoucherService {
    private VoucherRepository voucherRepository;
    private OrganizerService organizerService;
     public VoucherServiceImpl(VoucherRepository voucherRepository, OrganizerService organizerService){
         this.voucherRepository = voucherRepository;
         this.organizerService = organizerService;
     }

     @Override
    public void addVoucher(VoucherDto voucherDto){
         Organizer organizer = organizerService.getOrganizerById(voucherDto.getOrganizerId());
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

}
