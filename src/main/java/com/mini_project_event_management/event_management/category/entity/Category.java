package com.mini_project_event_management.event_management.category.entity;

import com.mini_project_event_management.event_management.block.dto.BlockDto;
import com.mini_project_event_management.event_management.block.entity.Block;
import com.mini_project_event_management.event_management.category.dto.CategoryDto;
import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.products.dto.ProductsDto;
import com.mini_project_event_management.event_management.products.entity.Products;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_gen")
    @SequenceGenerator(name = "category_id_gen", sequenceName = "category_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Min(value = 0, message = "Price must be zero or positive")
    @Column(name = "price", nullable = false)
    private int price;

    @Min(value = 0, message = "Quota must be zero or positive")
    @Column(name = "quota", nullable = false)
    private int quota;

    @NotNull(message = "Event cannot be null")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @NotBlank(message = "Name cannot be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Description cannot be empty")
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "description_detail")
    private String descriptionDetail;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Block> blocks;


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
    public CategoryDto toCategoryDto() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(this.id);
        categoryDto.setName(this.name);
        categoryDto.setEventId(this.event.getId());
        categoryDto.setDescription(this.description);
        categoryDto.setDescriptionDetail(this.descriptionDetail);
        categoryDto.setQuota(this.quota);
        categoryDto.setPrice(this.price);
        List<BlockDto> block = this.blocks.stream().map(Block::toBlockDto).collect(Collectors.toList());
        categoryDto.setBlocks(block);
        return categoryDto;
    }
}
