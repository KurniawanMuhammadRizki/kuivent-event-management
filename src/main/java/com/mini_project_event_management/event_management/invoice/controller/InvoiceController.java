package com.mini_project_event_management.event_management.invoice.controller;

import com.mini_project_event_management.event_management.invoice.dto.InvoiceDto;
import com.mini_project_event_management.event_management.invoice.service.InvoiceService;
import com.mini_project_event_management.event_management.invoice.service.impl.InvoiceServiceImpl;
import com.mini_project_event_management.event_management.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {
     private final InvoiceService invoiceService;
     public InvoiceController(InvoiceService invoiceService){
          this.invoiceService = invoiceService;
     }

     @PostMapping
     public ResponseEntity<Response<Object>> generateInvoice(@Validated @RequestBody InvoiceDto invoiceDto){
          invoiceService.generateInvoice(invoiceDto);
          return Response.successfulResponse("Invoice generated successfully");
     }
}
