package com.mini_project_event_management.event_management.invoice.service;

import com.mini_project_event_management.event_management.invoice.dto.InvoiceDto;
import com.mini_project_event_management.event_management.invoice.dto.InvoiceResponseDto;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceService {
     void generateInvoice(InvoiceDto invoiceDto);

     List<InvoiceResponseDto> getInvoiceByEventId(Long id);

     List<InvoiceResponseDto> getInvoiceByCompanyId(Long id);

     BigDecimal getIncomeByEventId(Long id);
}
