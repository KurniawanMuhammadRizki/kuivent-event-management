package com.mini_project_event_management.event_management.tickets.controller;

import com.mini_project_event_management.event_management.responses.Response;
import com.mini_project_event_management.event_management.tickets.dto.AddTicketDto;
import com.mini_project_event_management.event_management.tickets.dto.TicketDto;
import com.mini_project_event_management.event_management.tickets.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {
     private final TicketService ticketService;

     public TicketController(TicketService ticketService) {
          this.ticketService = ticketService;
     }

     @PostMapping
     public ResponseEntity<Response<Object>> addTicket(@Validated @RequestBody AddTicketDto ticketDto){
          ticketService.addTicket(ticketDto);
          return Response.successfulResponse("Ticket added successfully");
     }

     @GetMapping("/event/{id}")
     public ResponseEntity<Response<List<TicketDto>>> getTicketByEventId(@PathVariable Long id){
          List<TicketDto> ticketDtos = ticketService.getTicketByEventId(id);
          return Response.successfulResponse("Ticket fetched successfully", ticketDtos);
     }

     @GetMapping("/users/{id}")
     public ResponseEntity<Response<List<TicketDto>>> getTicketByUsersId(@PathVariable Long id){
          List<TicketDto> ticketDtos = ticketService.getTicketByUsersId(id);
          return Response.successfulResponse("Ticket fetched successfully", ticketDtos);
     }

     @GetMapping("/attendee/event/{id}")
     public ResponseEntity<Response<Integer>> getTotalAttendeeByEvent(@PathVariable Long id){
          Integer result = ticketService.CountByEventId(id);
          return Response.successfulResponse("Total Attendee  by event id:" , result);
     }
}
