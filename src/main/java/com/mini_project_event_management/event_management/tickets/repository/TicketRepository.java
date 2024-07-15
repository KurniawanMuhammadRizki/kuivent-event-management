package com.mini_project_event_management.event_management.tickets.repository;

import com.mini_project_event_management.event_management.tickets.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
     List<Ticket> findAllByEventId(Long id);
     List<Ticket> findAllByUsersId(Long id);
}
