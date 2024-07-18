package com.mini_project_event_management.event_management.users.entity;

import com.mini_project_event_management.event_management.users.dto.UsersDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.Instant;

@Data
@Entity
@Table(name = "users")
public class Users implements Serializable {
     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_gen")
     @SequenceGenerator(name = "users_id_gen", sequenceName = "users_id_seq", allocationSize = 1)
     @Column(name = "id", nullable = false)
     private Long id;

     @NotBlank(message = "First name cannot be empty")
     @Column(name = "first_name", nullable = false)
     private String firstName;

     @NotBlank(message = "Last name cannot be empty")
     @Column(name = "last_name", nullable = false)
     private String lastName;

     @NotBlank(message = "Password cannot be empty")
     @Column(name = "password", nullable = false)
     private String password;

     @NotBlank(message = "Address cannot be empty")
     @Column(name = "address", nullable = false)
     private String address;

     @NotBlank(message = "City cannot be empty")
     @Column(name = "city", nullable = false)
     private String city;

     @NotBlank(message = "Email cannot be empty")
     @Column(name = "email", nullable = false)
     private String email;

     @Column(name = "about")
     private String about;

     @Column(name = "profile_img_url")
     private String profileImgUrl;

     @NotBlank(message = "Phone number cannot be empty")
     @Column(name = "phone_number", nullable = false)
     private String phoneNumber;

     @NotBlank(message = "Slug number cannot be empty")
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

     public UsersDto toUsersDto() {
          UsersDto dto = new UsersDto();
          dto.setId(this.id);
          dto.setFirstName(this.firstName);
          dto.setLastName(this.lastName);
          dto.setAddress(this.address);
          dto.setCity(this.city);
          dto.setEmail(this.email);
          dto.setAbout(this.about);
          dto.setProfileImgUrl(this.profileImgUrl);
          dto.setPhoneNumber(this.phoneNumber);
          dto.setSlug(this.slug);
          return dto;
     }
}
