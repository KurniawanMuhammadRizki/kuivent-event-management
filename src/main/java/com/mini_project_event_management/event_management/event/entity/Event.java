package com.mini_project_event_management.event_management.event.entity;

import com.mini_project_event_management.event_management.eventType.entity.EventType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Data
@Entity
@Table(name = "event")
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_id_gen")
    @SequenceGenerator(name = "event_id_gen", sequenceName = "event_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Address cannot be empty")
    @Column(name = "address", nullable = false)
    private String address;

    @NotBlank(message = "City cannot be empty")
    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "website_url")
    private String websiteUrl;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "description")
    private String description;

    @NotNull(message = "Date start cannot be empty")
    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @NotNull(message = "Hour start cannot be empty")
    @Column(name = "hour_start")
    private Date hourStart;

    @NotNull(message = "Hour end cannot be empty")
    @Column(name = "hour_end")
    private Date hourEnd;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @NotNull(message = "Capacity amount cannot be null")
    @Min(value = 0, message = "Capacity amount must be zero or positive")
    @Column(name = "capacity", nullable = false)
    private int capacity;

    @NotNull(message = "Event type cannot be null")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_type_id", nullable = false)
    private EventType eventType;


}
