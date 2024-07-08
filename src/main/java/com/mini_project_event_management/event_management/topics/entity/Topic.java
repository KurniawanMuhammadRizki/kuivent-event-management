package com.mini_project_event_management.event_management.topics.entity;

import com.mini_project_event_management.event_management.topics.dto.TopicDto;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.Instant;

@Data
@Entity
@Table(name = "topics")
public class Topic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topics_id_gen")
    @SequenceGenerator(name = "topics_id_gen", sequenceName = "topics_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Name cannot be null")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Name cannot be null")
    @Column(name = "image_url", nullable = false)
    private String imgUrl;

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

    public TopicDto toTopicDto(){
        TopicDto dto = new TopicDto();
        dto.setName(this.getName());
        dto.setImgUrl(this.getImgUrl());
        return dto;
    }


}
