package com.mini_project_event_management.event_management.invoice.repository;

import com.mini_project_event_management.event_management.invoice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
