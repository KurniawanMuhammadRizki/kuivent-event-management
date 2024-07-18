package com.mini_project_event_management.event_management.invoice.repository;

import com.mini_project_event_management.event_management.invoice.entity.Invoice;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
     List<Invoice> findAllByEventId(Long id);

     List<Invoice> findAllByCompanyId(Long id);

     @Query("SELECT SUM(i.totalPrice) FROM Invoice i WHERE i.event.id = :eventId")
     BigDecimal sumTotalPriceByEventId(@Param("eventId") Long eventId);
}
