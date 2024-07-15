package com.mini_project_event_management.event_management.tickets.dto;

import com.mini_project_event_management.event_management.tickets.entity.Ticket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TicketDto {
     @NotNull(message = "Event  cannot be null")
     private Long eventId;
     private LocalDate dateStart;
     private LocalDate dateEnd;
     private LocalTime hourStart;
     private LocalTime hourEnd;
     @NotBlank(message = "City cannot be empty")
     private String city;
     @NotBlank(message = "Event type cannot be empty")
     private String eventType;
     @NotBlank(message = "Event name cannot be empty")
     private String eventName;

     @NotNull(message = "User  cannot be null")
     private Long userId;
     @NotBlank(message = "First name cannot be empty")
     private String firstName;
     @NotBlank(message = "Last name cannot be empty")
     private String lastName;
     @NotBlank(message = "Email cannot be empty")
     private String email;

     @NotBlank(message = "Category name cannot be empty")
     private String categoryName;
     @NotBlank(message = "Block name cannot be empty")
     private String blockName;
     private String ticketCode;

     private static final SecureRandom random = new SecureRandom();
     public Ticket toTicket() {
          Ticket ticket = new Ticket();
          ticket.setFirstName(firstName);
          ticket.setLastName(lastName);
          ticket.setHourEnd(hourEnd);
          ticket.setHourStart(hourStart);
          ticket.setDateStart(dateStart);
          ticket.setDateEnd(dateEnd);
          ticket.setCity(city);
          ticket.setEmail(email);
          ticket.setEventName(eventName);
          ticket.setEventType(eventType);
          String ticketCode = generateTicketCode(firstName);
          ticket.setTicketCode(ticketCode);

          return ticket;
     }
     private String generateTicketCode(String firstName) {
          String letter = firstName.length() < 3 ? firstName : firstName.substring(0, 3);
          int number = 100 + random.nextInt(900);
          return letter.toUpperCase() + number;
     }
}
