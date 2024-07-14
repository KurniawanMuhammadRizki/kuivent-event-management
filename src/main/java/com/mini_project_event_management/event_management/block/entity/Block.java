package com.mini_project_event_management.event_management.block.entity;

import com.mini_project_event_management.event_management.block.dto.BlockDto;
import com.mini_project_event_management.event_management.category.entity.Category;
import com.mini_project_event_management.event_management.event.entity.Event;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.Instant;

@Data
@Entity
@Table(name = "block")
public class Block implements Serializable {
     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "block_id_gen")
     @SequenceGenerator(name = "block_id_gen", sequenceName = "block_id_seq", allocationSize = 1)
     @Column(name = "id", nullable = false)
     private Long id;

     @NotNull(message = "Category cannot be null")
     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "category_id", nullable = false)
     private  Category category;

     @Column(name = "name", nullable = false)
     private  String name;

     @Column(name = "is_booked", nullable = false)
     private  boolean isBooked;

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

     public BlockDto toBlockDto() {
          BlockDto blockDto = new BlockDto();
          blockDto.setCategoryId(this.category.getId());
          blockDto.setName(this.name);
          blockDto.setBooked(this.isBooked);
          return blockDto;
     }
}
