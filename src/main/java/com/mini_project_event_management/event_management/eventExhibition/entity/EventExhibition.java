package com.mini_project_event_management.event_management.eventExhibition.entity;

import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.topics.entity.Topic;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Data
@Table(name = "event_exhibition")
public class EventExhibition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_exhibition_id_gen")
    @SequenceGenerator(name = "event_exhibition_id_gen", sequenceName = "event_exhibition_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "Company cannot be null")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @NotNull(message = "Event cannot be null")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @NotNull(message = "Topic cannot be null")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();

    @Column(name = "deleted_at")
    private Instant deletedAt;

}
