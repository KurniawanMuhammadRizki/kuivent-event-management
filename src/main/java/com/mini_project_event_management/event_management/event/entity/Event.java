package com.mini_project_event_management.event_management.event.entity;

import com.mini_project_event_management.event_management.category.dto.CategoryDto;
import com.mini_project_event_management.event_management.category.entity.Category;
import com.mini_project_event_management.event_management.company.dto.CompanyDto;
import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.event.dto.EventDto;
import com.mini_project_event_management.event_management.eventType.entity.EventType;
import com.mini_project_event_management.event_management.rating.dto.RatingDto;
import com.mini_project_event_management.event_management.rating.entity.Rating;
import com.mini_project_event_management.event_management.speakers.dto.SpeakerDto;
import com.mini_project_event_management.event_management.speakers.entity.Speakers;
import com.mini_project_event_management.event_management.topics.dto.TopicDto;
import com.mini_project_event_management.event_management.topics.entity.Topic;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

     @Column(name = "description_detail")
     private String descriptionDetail;

     @Column(name = "slug")
     private String slug;

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

     @ManyToMany(fetch = FetchType.LAZY)
     @JoinTable(name = "event_topic", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "topic_id"))
     private List<Topic> topics;

     @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
     private List<Category> categories;

     @ManyToMany(fetch = FetchType.LAZY)
     @JoinTable(name = "event_company", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "company_id"))
     private List<Company> companies;

     @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
     private List<Speakers> speakers;

     @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
     private List<Rating> ratings;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "event_type_id")
     private EventType eventType;

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

     public EventDto toEventDto() {
          EventDto eventDto = new EventDto();
          eventDto.setId(this.id);
          eventDto.setName(this.name);
          eventDto.setAddress(this.address);
          eventDto.setCity(this.city);
          eventDto.setWebsiteUrl(this.websiteUrl);
          eventDto.setImageUrl(this.imageUrl);
          eventDto.setDescription(this.description);
          eventDto.setSlug(this.slug);
          eventDto.setDescriptionDetail(this.descriptionDetail);
          eventDto.setDateStart(this.dateStart);
          eventDto.setDateEnd(this.dateEnd);
          eventDto.setHourStart(this.hourStart);
          eventDto.setHourEnd(this.hourEnd);
          eventDto.setCapacity(this.capacity);
          eventDto.setEventTypeId(this.eventType.getId());
          List<TopicDto> topics = this.topics.stream().map(Topic::toTopicDto).collect(Collectors.toList());
          eventDto.setTopics(topics);

          List<CompanyDto> companies = this.companies.stream().map(Company::toCompanyDto).toList();
          eventDto.setCompanies(companies);

          List<CategoryDto> categoryDtos = this.categories.stream()
                  .map(Category::toCategoryDto)
                  .collect(Collectors.toList());
          eventDto.setCategories(categoryDtos);

          List<SpeakerDto> speakers = this.speakers.stream().map(Speakers::toSpeakerDto).toList();
          eventDto.setSpeakers(speakers);

          List<RatingDto> ratings = this.ratings.stream().map(Rating::toRatingDto).toList();
          eventDto.setRatings(ratings);

          eventDto.setEventType(eventType.toEventTypeDto());

          return eventDto;
     }
}
