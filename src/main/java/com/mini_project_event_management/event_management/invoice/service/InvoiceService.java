package com.mini_project_event_management.event_management.invoice.service;

import com.mini_project_event_management.event_management.invoice.dto.InvoiceDto;

public interface InvoiceService {
     void generateInvoice(InvoiceDto invoiceDto);
}
