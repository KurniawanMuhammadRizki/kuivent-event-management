package com.mini_project_event_management.event_management.tickets.service.impl;

import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.event.service.EventService;
import com.mini_project_event_management.event_management.tickets.dto.TicketDto;
import com.mini_project_event_management.event_management.tickets.entity.Ticket;
import com.mini_project_event_management.event_management.tickets.repository.TicketRepository;
import com.mini_project_event_management.event_management.tickets.service.TicketService;
import com.mini_project_event_management.event_management.users.entity.Users;
import com.mini_project_event_management.event_management.users.service.UsersService;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {
     private final TicketRepository ticketRepository;
     private final EventService eventService;
     private final UsersService usersService;

     public TicketServiceImpl(TicketRepository ticketRepository, EventService eventService, UsersService usersService) {
          this.ticketRepository = ticketRepository;
          this.eventService = eventService;
          this.usersService = usersService;
     }

     @Override
     public void addTicket(TicketDto ticketDto) {
          Event event = eventService.getEventById(ticketDto.getEventId());
          Users user = usersService.getUserById(ticketDto.getUserId());
          Ticket ticket = ticketDto.toTicket();
          ticket.setEvent(event);
          ticket.setUsers(user);
          ticketRepository.save(ticket);
     }



}
