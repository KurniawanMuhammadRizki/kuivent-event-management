package com.mini_project_event_management.event_management.tickets.repository;

import com.mini_project_event_management.event_management.tickets.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
