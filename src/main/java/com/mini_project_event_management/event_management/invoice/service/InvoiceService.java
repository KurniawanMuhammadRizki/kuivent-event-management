package com.mini_project_event_management.event_management.invoice.service;

import com.mini_project_event_management.event_management.invoice.dto.InvoiceDto;
import com.mini_project_event_management.event_management.invoice.dto.InvoiceResponseDto;

import java.util.List;

public interface InvoiceService {
     void generateInvoice(InvoiceDto invoiceDto);
     List<InvoiceResponseDto> getInvoiceByEventId(Long id);
}
