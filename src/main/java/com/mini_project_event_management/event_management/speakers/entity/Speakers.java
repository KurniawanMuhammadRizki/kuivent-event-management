package com.mini_project_event_management.event_management.speakers.entity;

import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.speakers.dto.SpeakerDto;
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
@Table(name = "speakers")
public class Speakers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "speakers_id_gen")
    @SequenceGenerator(name = "speakers_id_gen", sequenceName = "speakers_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "slug")
    private String slug;

    @Column(name = "about")
    private String about;

    @Column(name = "position")
    private String position;

    @Column(name = "company_name")
    private String companyName;

    @NotNull(message = "Event id cannot be null")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
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

    public SpeakerDto toSpeakerDto(){
        SpeakerDto dto = new SpeakerDto();
        dto.setAbout(this.about);
        dto.setName(this.name);
        dto.setCompanyName(this.companyName);
        dto.setProfileImgUrl(this.profileImageUrl);
        dto.setSlug(this.slug);
        dto.setPosition(this.position);
        dto.setEventId(this.event.getId());
        return dto;
    }
}
