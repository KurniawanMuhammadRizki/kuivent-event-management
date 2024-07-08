package com.mini_project_event_management.event_management.tickets.controller;

import com.mini_project_event_management.event_management.responses.Response;
import com.mini_project_event_management.event_management.tickets.dto.TicketDto;
import com.mini_project_event_management.event_management.tickets.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {
     private final TicketService ticketService;

     public TicketController(TicketService ticketService) {
          this.ticketService = ticketService;
     }

     @PostMapping
     public ResponseEntity<Response<Object>> addTicket(@Validated @RequestBody TicketDto ticketDto){
          ticketService.addTicket(ticketDto);
          return Response.successfulResponse("Ticket added successfully");
     }
}
