package com.mini_project_event_management.event_management.eventExhibition.service.impl;

import com.mini_project_event_management.event_management.company.entity.Company;
import com.mini_project_event_management.event_management.company.service.CompanyService;
import com.mini_project_event_management.event_management.event.entity.Event;
import com.mini_project_event_management.event_management.event.service.EventService;
import com.mini_project_event_management.event_management.eventExhibition.dto.EventExhibitionDto;
import com.mini_project_event_management.event_management.eventExhibition.entity.EventExhibition;
import com.mini_project_event_management.event_management.eventExhibition.repository.EventExhibitionRepository;
import com.mini_project_event_management.event_management.eventExhibition.service.EventExhibitionService;
import com.mini_project_event_management.event_management.topics.entity.Topic;
import com.mini_project_event_management.event_management.topics.service.TopicsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class EventExhibitionServiceImpl implements EventExhibitionService {
    private final EventExhibitionRepository eventExhibitionRepository;
    private final EventService eventService;
    private final CompanyService companyService;
    private final TopicsService topicsService;

    public EventExhibitionServiceImpl(EventExhibitionRepository eventExhibitionRepository, EventService eventService, CompanyService companyService, TopicsService topicsService){
        this.eventExhibitionRepository = eventExhibitionRepository;
        this.companyService = companyService;
        this.eventService = eventService;
        this.topicsService = topicsService;
    }

    Instant now = Instant.now();
    @Override
    @Transactional
    public void addEventExhibition(EventExhibitionDto eventExhibitionDto){
        Company company = companyService.getCompanyById(eventExhibitionDto.getCompanyId());
        Event event = eventService.getEventById(eventExhibitionDto.getEventId());
        Topic  topic = topicsService.getTopicById(eventExhibitionDto.getTopicId());

        EventExhibition  eventExhibition = new EventExhibition();
        eventExhibition.setEvent(event);
        eventExhibition.setCompany(company);
        eventExhibition.setTopic(topic);
        eventExhibition.setCreatedAt(now);
        eventExhibition.setUpdatedAt(now);

        eventExhibitionRepository.save(eventExhibition);
    }
}
