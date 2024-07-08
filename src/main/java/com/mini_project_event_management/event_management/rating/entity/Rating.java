package com.mini_project_event_management.event_management.rating.entity;

import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.eventType.entity.EventType;
import com.mini_project_event_management.event_management.rating.dto.RatingDto;
import com.mini_project_event_management.event_management.users.entity.Users;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.Date;

@Data
@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_id_gen")
    @SequenceGenerator(name = "rating_id_gen", sequenceName = "rating_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "User id empty")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotNull(message = "Event id cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @NotNull(message = "Rating empty")
    @Column(name = "rating", nullable = false)
    private int rating;

    @NotBlank(message = "Review cannot be empty")
    @Column(name = "review", nullable = false)
    private String review;
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
    public RatingDto toRatingDto(){
        RatingDto dto = new RatingDto();
        dto.setRating(this.rating);
        dto.setReview(this.review);
        return dto;
    }
}
