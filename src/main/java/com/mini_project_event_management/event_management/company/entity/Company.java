package com.mini_project_event_management.event_management.company.entity;

import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.Instant;

@Data
@Entity
@Table(name = "company")
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_gen")
    @SequenceGenerator(name = "company_id_gen", sequenceName = "company_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Password cannot be empty")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "profile_url")
    private String profileUrl;

    @NotBlank(message = "Email cannot be empty")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "about")
    private String about;

    @NotBlank(message = "Phone number cannot be empty")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotBlank(message = "Address cannot be empty")
    @Column(name = "address", nullable = false)
    private String address;

    @NotBlank(message = "City cannot be empty")
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank(message = "Website url cannot be empty")
    @Column(name = "website_url", nullable = false)
    private String websiteUrl;

    @NotBlank(message = "Slug url cannot be empty")
    @Column(name = "slug", nullable = false)
    private String slug;

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
