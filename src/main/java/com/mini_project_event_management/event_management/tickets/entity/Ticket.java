package com.mini_project_event_management.event_management.tickets.entity;

import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.eventType.entity.EventType;
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

     @Column(name = "date_start", nullable = false)
     private LocalDate dateStart;

     @Column(name = "date_end", nullable = false)
     private LocalDate dateEnd;

     @Column(name = "hour_start", nullable = false)
     private LocalTime hourStart;

     @Column(name = "hour_end", nullable = false)
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

}
