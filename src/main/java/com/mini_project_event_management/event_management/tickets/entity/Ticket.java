package com.mini_project_event_management.event_management.tickets.entity;

import com.mini_project_event_management.event_management.block.entity.Block;
import com.mini_project_event_management.event_management.category.entity.Category;
import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.eventType.entity.EventType;
import com.mini_project_event_management.event_management.tickets.dto.TicketDto;
import com.mini_project_event_management.event_management.users.entity.Users;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {
     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_id_gen")
     @SequenceGenerator(name = "ticket_id_gen", sequenceName = "ticket_id_seq", allocationSize = 1)
     @Column(name = "id", nullable = false)
     private Long id;

     @NotNull(message = "Event cannot be null")
     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "event_id", nullable = false)
     private Event event;

     @NotNull(message = "User cannot be null")
     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "user_id", nullable = false)
     private Users users;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "category_id", nullable = false)
     private Category category;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "block_id")
     private Block block;

     @Column(name = "date_start", nullable = false)
     private LocalDate dateStart;

     @Column(name = "date_end")
     private LocalDate dateEnd;

     @Column(name = "hour_start", nullable = false)
     private LocalTime hourStart;

     @Column(name = "hour_end")
     private LocalTime hourEnd;

     @Column(name = "first_name", nullable = false)
     private String firstName;

     @Column(name = "last_name", nullable = false)
     private String lastName;

     @Column(name = "email", nullable = false)
     private String email;

     @Column(name = "event_name", nullable = false)
     private String eventName;

     @Column(name = "city", nullable = false)
     private String city;

     @Column(name = "ticket_code", nullable = false)
     private String ticketCode;

     @Column(name = "event_type", nullable = false)
     private String eventType;

     @Column(name = "category_name", nullable = false)
     private String categoryName;

     @Column(name = "block_name", nullable = false)
     private String blockName;

     @ColumnDefault("CURRENT_TIMESTAMP")
     @Column(name = "created_at")
     private Instant createdAt = Instant.now();

     @ColumnDefault("CURRENT_TIMESTAMP")
     @Column(name = "updated_at")
     private Instant updatedAt = Instant.now();

     @Column(name = "deleted_at")
     private Instant deletedAt;

     @PrePersist
     void onSave() {
          this.createdAt = Instant.now();
          this.updatedAt = Instant.now();
     }

     @PreUpdate
     void onUpdate() {
          this.updatedAt = Instant.now();
     }

     @PreDestroy
     void onDelete() {
          this.deletedAt = Instant.now();
     }

     public static TicketDto toTicketDto(Ticket ticket){
          TicketDto dto = new TicketDto();
          dto.setEventId(ticket.event.getId());
          dto.setUserId(ticket.users.getId());
          dto.setDateEnd(ticket.dateEnd);
          dto.setDateStart(ticket.dateStart);
          dto.setHourStart(ticket.hourStart);
          dto.setHourEnd(ticket.hourEnd);
          dto.setFirstName(ticket.firstName);
          dto.setLastName(ticket.lastName);
          dto.setEmail(ticket.users.getEmail());
          dto.setEventName(ticket.eventName);
          dto.setCity(ticket.city);
          dto.setEventType(ticket.eventType);
          dto.setTicketCode(ticket.ticketCode);
          dto.setBlockName(ticket.blockName);
          dto.setCategoryName(ticket.categoryName);

          return dto;
     }
}
